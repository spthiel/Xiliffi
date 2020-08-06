package me.elspeth.language.xiliffy.psi;

import com.intellij.psi.tree.IElementType;
import me.elspeth.language.xiliffy.XiliffyLanguage;
import org.jetbrains.annotations.NotNull;

public class XiliffyTokenType extends IElementType {

    public XiliffyTokenType(@NotNull String debugName) {
        super(debugName, XiliffyLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "XiliffyTokenType." + super.toString();
    }
}
