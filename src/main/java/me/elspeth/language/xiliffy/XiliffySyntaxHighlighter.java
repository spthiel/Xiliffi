package me.elspeth.language.xiliffy;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import me.elspeth.language.xiliffy.psi.XiliffyTypes;
import org.jetbrains.annotations.NotNull;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class XiliffySyntaxHighlighter extends SyntaxHighlighterBase {
    public static final TextAttributesKey SEPARATOR =
            createTextAttributesKey("XILIFFY_SEPARATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN);
    public static final TextAttributesKey KEY =
            createTextAttributesKey("XILIFFY_KEY", DefaultLanguageHighlighterColors.MARKUP_ATTRIBUTE);
    public static final TextAttributesKey VALUE =
            createTextAttributesKey("XILIFFY_VALUE", DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey NOTE =
            createTextAttributesKey("XILIFFY_NOTE", DefaultLanguageHighlighterColors.BLOCK_COMMENT);
    public static final TextAttributesKey COMMENT =
            createTextAttributesKey("XILIFFY_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey CLASSKEY =
            createTextAttributesKey("XILIFFY_CLASSKEY", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey BAD_CHARACTER =
            createTextAttributesKey("XILIFFY_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);


    private static final TextAttributesKey[] BAD_CHAR_KEYS = new TextAttributesKey[]{BAD_CHARACTER};
    private static final TextAttributesKey[] SEPARATOR_KEYS = new TextAttributesKey[]{SEPARATOR};
    private static final TextAttributesKey[] KEY_KEYS = new TextAttributesKey[]{KEY};
    private static final TextAttributesKey[] VALUE_KEYS = new TextAttributesKey[]{VALUE};
    private static final TextAttributesKey[] CLASS_KEYS = new TextAttributesKey[]{CLASSKEY};
    private static final TextAttributesKey[] NOTE_KEYS = new TextAttributesKey[]{NOTE};
    private static final TextAttributesKey[] COMMENT_KEYS = new TextAttributesKey[]{COMMENT};
    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new XiliffyLexerAdapter();
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        if (tokenType.equals(XiliffyTypes.SEPARATOR)) {
            return SEPARATOR_KEYS;
        } else if (tokenType.equals(XiliffyTypes.KEY)) {
            return KEY_KEYS;
        } else if (tokenType.equals(XiliffyTypes.VALUE)) {
            return VALUE_KEYS;
        } else if (tokenType.equals(XiliffyTypes.NOTE)) {
            return NOTE_KEYS;
        } else if (tokenType.equals(XiliffyTypes.NOTEKEY)) {
            return NOTE_KEYS;
        } else if (tokenType.equals(XiliffyTypes.NOTEVALUE)) {
            return NOTE_KEYS;
        } else if (tokenType.equals(XiliffyTypes.CLASSKEY)) {
            return CLASS_KEYS;
        } else if (tokenType.equals(XiliffyTypes.COMMENT)) {
            return COMMENT_KEYS;
        } else if (tokenType.equals(TokenType.BAD_CHARACTER)) {
            return BAD_CHAR_KEYS;
        } else {
            return EMPTY_KEYS;
        }
    }
}
