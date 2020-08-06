package me.elspeth.language.xiliffy;

import com.intellij.lang.Language;

public class XiliffyLanguage extends Language {
    public static final XiliffyLanguage INSTANCE = new XiliffyLanguage();

    protected XiliffyLanguage() {
        super("Xiliffy");
    }
}
