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
    "abstract"                  {return sym(ABSTRACT);}
    "nullable"                  {return sym(NULLABLE);}
    "package"                   {return sym(PACKAGE);}
    "private"                   {return sym(PRIVATE);}
    "public"                    {return sym(PUBLIC);}
    "static"                    {return sym(STATIC);}
    "import"                    {return sym(IMPORT);}
    "final"                     {return sym(FINAL);}
    "class"                     {return sym(CLASS);}
    "def"                       {return sym(DEF);}
    "->"                        {return sym(ARROW);}
    "@"                         {return sym(AT);}
    "="                         {return sym(EQ);}
    "["                         {return sym(LBRACKET);}
    "]"                         {return sym(RBRACKET);}
    "{"                         {return sym(LBRACE);}
    "}"                         {return sym(RBRACE);}
    "("                         {return sym(LPAREN);}
    ")"                         {return sym(RPAREN);}
    "."                         {return sym(DOT);}
    ","                         {return sym(COMMA);}

    {Identifier}                {return sym(IDENTIFIER, yytext());}
    {WhiteSpace}                {}
    {Comment}                   {}

}

<<EOF>> {
    return sym(EOF);
}
