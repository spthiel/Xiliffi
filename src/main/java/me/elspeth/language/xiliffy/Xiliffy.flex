package me.elspeth.language.xiliffy;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import me.elspeth.language.xiliffy.psi.XiliffyTypes;
import com.intellij.psi.TokenType;

%%

%class XiliffyLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

CRLF=\R|[$]
WHITE_SPACE=[\ \t]
END_OF_LINE_COMMENT=("#")[^\r\n]*
SEPARATOR=[:]
KEY_CHARACTER=[a-zA-Z]
CLASS_CHARACTER=[a-zA-Z_]
CLASSSTART=[.]
MULTILINEVALUESTART=[\"]
MULTILINEVALUE=([^\"] | \\\")+
SINGLELINEVALUE=[^\ \t\n\"][^\n]*

%state LINE
%state WHITESPACE
%state CLASS
%state KEY
%state SEPARATOR
%state NOTESEPARATOR
%state VALUE
%state NOTEVALUE
%state LINEEND

%%

<YYINITIAL> {WHITE_SPACE}+ { yybegin(YYINITIAL); return XiliffyTypes.WHITESPACE;}
<YYINITIAL> {CRLF} { yybegin(YYINITIAL); return XiliffyTypes.CRLF;}
<YYINITIAL> {END_OF_LINE_COMMENT} { yybegin(LINEEND); return XiliffyTypes.COMMENT;}
<YYINITIAL> {CLASSSTART}{CLASS_CHARACTER}+ { yybegin(LINEEND); return XiliffyTypes.CLASSKEY;}
<YYINITIAL> "note" { yybegin(NOTESEPARATOR); return XiliffyTypes.NOTEKEY;}
<YYINITIAL> {KEY_CHARACTER}+ { yybegin(SEPARATOR); return XiliffyTypes.KEY;}

<SEPARATOR> {WHITE_SPACE}+ {yybegin(SEPARATOR); return XiliffyTypes.SEPARATOR;}
<SEPARATOR> {SEPARATOR} {yybegin(VALUE); return XiliffyTypes.SEPARATOR;}

<NOTESEPARATOR> {WHITE_SPACE}+ {yybegin(NOTESEPARATOR); return XiliffyTypes.SEPARATOR;}
<NOTESEPARATOR> {SEPARATOR} {yybegin(NOTEVALUE); return XiliffyTypes.SEPARATOR;}

<VALUE> {WHITE_SPACE}+ {yybegin(VALUE); return XiliffyTypes.WHITESPACE;}
<VALUE> {MULTILINEVALUESTART}{MULTILINEVALUE}{MULTILINEVALUESTART} {yybegin(LINEEND); return XiliffyTypes.VALUE;}
<VALUE> {SINGLELINEVALUE} {yybegin(LINEEND); return XiliffyTypes.VALUE;}

<NOTEVALUE> {WHITE_SPACE}+ {yybegin(NOTEVALUE); return XiliffyTypes.WHITESPACE;}
<NOTEVALUE> {MULTILINEVALUESTART}{MULTILINEVALUE}{MULTILINEVALUESTART} {yybegin(LINEEND); return XiliffyTypes.NOTEVALUE;}
<NOTEVALUE> {SINGLELINEVALUE} {yybegin(LINEEND); return XiliffyTypes.NOTEVALUE;}

<LINEEND> {END_OF_LINE_COMMENT} {yybegin(LINEEND); return XiliffyTypes.COMMENT;}
<LINEEND> {WHITE_SPACE}+ {yybegin(LINEEND); return XiliffyTypes.WHITESPACE;}
<LINEEND> {CRLF} {yybegin(YYINITIAL); return XiliffyTypes.CRLF;}


[^]                                                         { return TokenType.BAD_CHARACTER; }