package me.elspeth.language.xiliffy;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.*;
import org.jetbrains.annotations.*;

import javax.swing.*;
import java.util.Map;

public class XiliffyColorSettingsPage implements ColorSettingsPage {
    private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[]{
            new AttributesDescriptor("Key", XiliffySyntaxHighlighter.KEY),
            new AttributesDescriptor("Separator", XiliffySyntaxHighlighter.SEPARATOR),
            new AttributesDescriptor("Value", XiliffySyntaxHighlighter.VALUE),
            new AttributesDescriptor("Note", XiliffySyntaxHighlighter.NOTE),
            new AttributesDescriptor("Comment", XiliffySyntaxHighlighter.COMMENT),
            new AttributesDescriptor("Class", XiliffySyntaxHighlighter.CLASSKEY),
            new AttributesDescriptor("Bad Value", XiliffySyntaxHighlighter.BAD_CHARACTER)
    };

    @Nullable
    @Override
    public Icon getIcon() {
        return XiliffyIcons.FILE;
    }

    @NotNull
    @Override
    public SyntaxHighlighter getHighlighter() {
        return new XiliffySyntaxHighlighter();
    }

    @NotNull
    @Override
    public String getDemoText() {
        return "source: en\n" +
                "product: exampleproduct\n" +
                "\n" +
                ".apple\n" +
                "    en: \"Apple\"\n" +
                "    de: \"Apfel\"\n" +
                "    .peel\n" +
                "        note: \"These translations will be listed as apple.peel\"\n" +
                "        en: \"peel\"\n" +
                "        de: \"Schale\"\n" +
                "\n" +
                "# This in an example comment\n" +
                ".example\n" +
                "    en: \"Example\"\n" +
                "    de: \"Beispiel\"";
    }

    @Nullable
    @Override
    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }

    @NotNull
    @Override
    public AttributesDescriptor[] getAttributeDescriptors() {
        return DESCRIPTORS;
    }

    @NotNull
    @Override
    public ColorDescriptor[] getColorDescriptors() {
        return ColorDescriptor.EMPTY_ARRAY;
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return "Xiliffy";
    }
}
