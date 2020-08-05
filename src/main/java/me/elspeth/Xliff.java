package me.elspeth;

import java.time.Instant;
import java.util.HashMap;

public class Xliff {

    private final HashMap<String, Translation> keys;
    private final String source;
    private final String target;
    private final String product;

    public Xliff(String source, XliffObject xliff) {
        this(source, null, xliff);
    }

    public Xliff(String source, String target, XliffObject xliff) {
        keys = new HashMap<>();
        this.source = source;
        this.target = target;
        this.product = xliff.getProduct();
        HashMap<String, HashMap<String, String>> translations = xliff.getTranslationHashMap();
        translations.forEach((key, value) -> {
            Translation toPut = new Translation(key);
            if (value.containsKey(source)) {
                toPut.setSource(value.get(source));
            } else {
                throw new IllegalStateException("No source translation for " + key + " found.");
            }
            keys.put(key, toPut);
            toPut.setNote(value.get("note"));
            if (target == null) {
                return;
            }
            toPut.setTarget(value.get(target));
        });

    }

    public boolean hasTarget() {
        return target != null;
    }

    public String getTarget() {
        return target;
    }

    public String asFile() {
        StringBuilder out = new StringBuilder();


        // HEAD
        out.append("<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\" ?>\n");
        out.append("<xliff version=\"1.0\">\n");
        out.append("\t<file source-language=\"").append(source).append("\" ");
        if(target != null) {
            out.append("target-language=\"").append(target).append("\" ");
        }
        out.append("datatype=\"plaintext\" original=\"messages\" date=\"").append(Instant.now().toString()).append("\" product-name=\"").append(product).append("\">\n");
        out.append("\t\t<header/>\n");
        out.append("\t\t<body>\n");

        // BODY

        keys.forEach((key, value) -> {
            out.append("\t\t\t<trans-unit id=\"").append(key).append("\">\n");
            out.append("\t\t\t\t<source>").append(value.getSource()).append("</source>\n");
            if(value.getNote() != null) {
                out.append("\t\t\t\t<note>").append(value.getNote()).append("</note>\n");
            }
            if(value.getTarget() != null) {
                out.append("\t\t\t\t<target>").append(value.getTarget()).append("</target>\n");
            }
            out.append("\t\t\t</trans-unit>\n");
        });

        // FOOTER

        out.append("\t\t</body>\n");
        out.append("\t</file>\n");
        out.append("</xliff>");

        return out.toString();
    }

    @Override
    public String toString() {
        return "Xliff{" +
                "keys=" + keys +
                ", source='" + source + '\'' +
                ", target='" + target + '\'' +
                '}';
    }
}
