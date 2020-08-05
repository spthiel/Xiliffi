package me.elspeth;

import java.io.*;
import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.Stack;
import java.util.regex.Pattern;

public class Parser {

    private static final Pattern whitespacePattern = Pattern.compile("^(\\t*)(.*)\\s*$");
    private static final Pattern classPattern = Pattern.compile("\\.(\\w+)");

    public static XliffObject readXLIFFYFile(File file) {
        XliffObject out = new XliffObject();

        Stack<String> key = new Stack<>();

        try (InputStreamReader reader = new InputStreamReader(new FileInputStream(file), "UTF-8")) {

            int charInt;
            int prevWhiteSpace = 0;
            int whiteSpaceDiff = 0;
            short currentWhiteSpace = 0;
            boolean inKey = false;
            boolean inLangId = false;
            int inLangText = 0;
            boolean inWhiteSpace = false;
            boolean inFillWhiteSpace = false;

            Entry currentTranslation = null;

            StringBuilder current = new StringBuilder();
            boolean oneMore = true;

            LinkedList<String> languages = new LinkedList<>();

            while ((charInt = reader.read()) != 0xffffffff || oneMore) {

                char c;
                if (charInt == 0xffffffff) {
                    c = '\n';
                    oneMore = false;
                } else {
                    c = (char) charInt;
                }

                if (c == '\r') {
                    continue;
                }

                if (inFillWhiteSpace) {
                    if (c == '\t' || c == ' ') {
                        continue;
                    }
                    inFillWhiteSpace = false;
                }

                if (!inKey && !inLangId && inLangText == 0) {
                    if (c == '#') {
                        eat(reader, '\n');
                        currentWhiteSpace = 0;
                        inWhiteSpace = true;
                        continue;
                    }
                }

                if (inWhiteSpace) {
                    if (c == '\t' || c == ' ') {
                        currentWhiteSpace++;
                        continue;
                    } else {
                        if (c == '\n') {
                            currentWhiteSpace = 0;
                            continue;
                        }
                        inWhiteSpace = false;
                        if (whiteSpaceDiff == 0) {
                            whiteSpaceDiff = currentWhiteSpace - prevWhiteSpace;
                        } else if (currentWhiteSpace % whiteSpaceDiff != 0) {
                            throw new IllegalStateException("Found invalid amount of whitespace");
                        }
                        if (currentWhiteSpace < prevWhiteSpace) {
                            for (int i = currentWhiteSpace; i < prevWhiteSpace; i += whiteSpaceDiff) {
                                try {
                                    key.pop();
                                } catch (EmptyStackException e) {
                                    System.out.println(c + " " + out + " " + currentWhiteSpace + " " + prevWhiteSpace);
                                    throw e;
                                }
                            }
                        }
                        prevWhiteSpace = currentWhiteSpace;
                        currentWhiteSpace = 0;
                    }
                }
                if (inKey || inLangId) {
                    if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') {
                        current.append(c);
                    } else {
                        if (inKey) {
                            if (c != '\n') {
                                eat(reader, '\n');
                            }
                            key.push(current.toString());
                            inKey = false;
                            inWhiteSpace = true;
                            current = new StringBuilder();
                        } else {
                            if (c != ':') {
                                eat(reader, ':');
                            }
                            inFillWhiteSpace = true;
                            inLangText = 1;
                            inLangId = false;
                            currentTranslation = new Entry(current.toString());
                            current = new StringBuilder();
                        }
                    }
                    continue;
                }
                if (inLangText > 1) {
                    if (inLangText == 3) {
                        if (c == '\n') {
                            addTranslation(out, key, currentTranslation, current);
                            current = new StringBuilder();
                            inWhiteSpace = true;
                            inLangText = 0;
                        } else {
                            current.append(c);
                        }
                    } else {
                        if (c == '\\') {
                            current.append((char) reader.read());
                        } else if (c == '"') {
                            inLangText = 0;
                            inWhiteSpace = true;
                            addTranslation(out, key, currentTranslation, current);
                            current = new StringBuilder();
                            eat(reader, '\n');
                        } else {
                            current.append(c);
                        }
                    }
                    continue;
                } else if (inLangText == 1) {
                    if (c == '"') {
                        inLangText = 2;
                    } else {
                        current.append(c);
                        inLangText = 3;
                    }
                    continue;
                }

                if (c == '.') {
                    inKey = true;
                } else {
                    inLangId = true;
                    current.append(c);
                }

            }

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
            System.exit(4);
        }

        return out;

    }

    private static void addTranslation(XliffObject out, Stack<String> key, Entry currentTranslation, StringBuilder current) {
        currentTranslation.setValue(current.toString());
        if (key.size() == 0) {
            switch (currentTranslation.getKey()) {
                case "source":
                    out.setSource(currentTranslation.getValue());
                    break;
                case "product":
                    out.setProduct(currentTranslation.getValue());
                    break;
            }
        } else {
            out.addTranslation(joinStack(key, "."), currentTranslation);
        }
    }

    private static String eat(Reader reader, char c) throws IOException {
        int intChar = 0;
        StringBuilder current = new StringBuilder();
        while ((intChar = reader.read()) != 0xffffffff) {
            if ((char) intChar == c) {
                return current.toString();
            }
            current.append((char) intChar);
        }
        if (c == '\n') {
            return current.toString();
        }
        throw new IllegalStateException("Reached end of file in eat for '" + c + "' - Ate: '" + current + "'");
    }

    private static String joinStack(Stack<String> stack, String glue) {
        StringBuilder out = new StringBuilder();
        boolean first = true;
        for (String str : stack) {
            if (!first) {
                out.append(glue);
            }
            first = false;
            out.append(str);
        }
        return out.toString();
    }

}
