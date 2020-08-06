package me.elspeth.language.xiliffy.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import me.elspeth.language.xiliffy.XiliffyFileType;
import me.elspeth.language.xiliffy.XiliffyLanguage;
import org.jetbrains.annotations.NotNull;

public class XiliffyFile extends PsiFileBase {


    public XiliffyFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, XiliffyLanguage.INSTANCE);
    }

    @Override
    public @NotNull FileType getFileType() {
        return XiliffyFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "Xiliffy File";
    }
}
