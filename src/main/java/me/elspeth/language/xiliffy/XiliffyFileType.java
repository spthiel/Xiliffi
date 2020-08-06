package me.elspeth.language.xiliffy;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class XiliffyFileType extends LanguageFileType {
    public static final XiliffyFileType INSTANCE = new XiliffyFileType();

    public XiliffyFileType() {
        super(XiliffyLanguage.INSTANCE);
    }

    @Override
    @NotNull
    public String getName() {
        return "Xiliffy File";
    }

    @Override
    @NotNull
    public String getDescription() {
        return "Xiliffy language file";
    }

    @Override
    @NotNull
    public String getDefaultExtension() {
        return "xiliffy";
    }

    @Override
    @Nullable
    public Icon getIcon() {
        return null;
    }
}
