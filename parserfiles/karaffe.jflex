/*
    Karaffe Programming Langage Lexer Specification
*/

package karaffe.compiler.phase.parser;
import java_cup.runtime.*;

%%
%public
%class Lexer
%implements sym

%unicode

%cup

%line
%column

%{
    private Symbol sym(int type) {
        return new Symbol(type, yyline + 1, yycolumn + 1);
    }
    
    private Symbol sym(int type, Object value) {
        return new Symbol(type, yyline + 1, yycolumn + 1, value);
    }

    public int line() {
        return yyline;
    }

    public int column() {
        return yycolumn;
    }

%}

LineTerminator = \r
               | \n
               | \r\n

InputCharacter = [^\r\n]

WhiteSpace = {LineTerminator}
           | [ \t\f]

Comment = {TraditionalComment}
        | {SingleLineComment}
        | {DocumentationComment}

TraditionalComment = "/*" [^*] ~"*/"
                   | "/*" "*"+ "/"
SingleLineComment = "//" {InputCharacter}* {LineTerminator}?
DocumentationComment = "/**" "*"* [^/*] ~"*/"

Identifier = [:jletter:][:jletterdigit:]*

%%
<YYINITIAL> {
    "package"                   {return sym(PACKAGE);}
    "import"                    {return sym(IMPORT);}
    "->"                        {return sym(ARROW);}
    "{"                         {return sym(LBRACE);}
    "}"                         {return sym(RBRACE);}
    "."                         {return sym(DOT);}
    ","                         {return sym(COMMA);}

    {Identifier}                {return sym(IDENTIFIER, yytext());}
    {WhiteSpace}                {}
    {Comment}                   {}

}

<<EOF>> {
    return sym(EOF);
}
