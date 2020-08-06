package me.elspeth.language.xiliffy;

import com.intellij.lexer.FlexAdapter;

public class XiliffyLexerAdapter extends FlexAdapter {

    public XiliffyLexerAdapter() {
        super(new XiliffyLexer(null));
    }
}
