package me.elspeth.language.xiliffy.psi;

import com.intellij.psi.tree.IElementType;
import me.elspeth.language.xiliffy.XiliffyLanguage;
import org.jetbrains.annotations.NotNull;

public class XiliffyElementType extends IElementType {

    public XiliffyElementType(@NotNull String debugName) {
        super(debugName, XiliffyLanguage.INSTANCE);
    }
}
