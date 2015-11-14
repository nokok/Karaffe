/*
    Karaffe Programming Langage Lexer Specification
*/

package karaffe.compiler;
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

    private final java.lang.StringBuilder stringBuilder = new java.lang.StringBuilder();

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

IntLiteral = [1-9][0-9]*
           | "0"

Identifier = [:jletter:][:jletterdigit:]*

%state STRING

%%
<YYINITIAL> {

    "protected"                 {return sym(PROTECTED);}
    "abstract"                  {return sym(ABSTRACT);}
    "package"                   {return sym(PACKAGE);}
    "private"                   {return sym(PRIVATE);}
    "public"                    {return sym(PUBLIC);}
    "static"                    {return sym(STATIC);}
    "import"                    {return sym(IMPORT);}
    "final"                     {return sym(FINAL);}
    "class"                     {return sym(CLASS);}
    "while"                     {return sym(WHILE);}
    "false"                     {return sym(FALSE);}
    "goto"                      {return sym(GOTO);}
    "main"                      {return sym(MAIN);}
    "true"                      {return sym(TRUE);}
    "else"                      {return sym(ELSE);}
    "not"                       {return sym(NOT);}
    "def"                       {return sym(DEF);}
    "if"                        {return sym(IF);}
    "is"                        {return sym(IS);}
    "=="                        {return sym(EQEQ);}
    "!="                        {return sym(NEQ);}
    "<="                        {return sym(LTE);}
    ">="                        {return sym(GTE);}
    "@"                         {return sym(AT);}
    "="                         {return sym(EQ);}
    "<"                         {return sym(LT);}
    ">"                         {return sym(GT);}
    "{"                         {return sym(LBRACE);}
    "}"                         {return sym(RBRACE);}
    "("                         {return sym(LPAREN);}
    ")"                         {return sym(RPAREN);}
    "["                         {return sym(LBRACKET);}
    "]"                         {return sym(RBRACKET);}
    ","                         {return sym(COMMA);}
    "."                         {return sym(DOT);}
    "+"                         {return sym(PLUS);}
    "-"                         {return sym(MINUS);}
    "*"                         {return sym(STAR);}
    "/"                         {return sym(SLASH);}
    ":"                         {return sym(COLON);}
    ";"                         {return sym(SEMICOLON);}

    {Identifier}                {return sym(IDENTIFIER, yytext());}
    {WhiteSpace}                {}
    {Comment}                   {}
    {IntLiteral}                {return sym(INT_LITERAL, yytext());}
    \"                          {stringBuilder.setLength(0); yybegin(STRING);}
}

<STRING> {
    \"                          {yybegin(YYINITIAL);return sym(STRING_LITERAL, stringBuilder.toString());}
    [^\n\r\"\\]+                {stringBuilder.append(yytext());}
    \\t                         {stringBuilder.append('\t');}
    \\n                         {stringBuilder.append('\n');}
    \\r                         {stringBuilder.append('\r');}
    \\\"                        {stringBuilder.append('\"');}
    \\                          {stringBuilder.append('\\');}
}

[^]                             {throw new Error("Illegal Character" + yytext());}

<<EOF>> {
    return sym(EOF);
}
