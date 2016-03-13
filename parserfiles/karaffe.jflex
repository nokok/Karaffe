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

    "abstract"                  {return sym(ABSTRACT);}
    "package"                   {return sym(PACKAGE);}
    "private"                   {return sym(PRIVATE);}
    "public"                    {return sym(PUBLIC);}
    "static"                    {return sym(STATIC);}
    "import"                    {return sym(IMPORT);}
    "const"                     {return sym(CONST);}
    "final"                     {return sym(FINAL);}
    "class"                     {return sym(CLASS);}
    "while"                     {return sym(WHILE);}
    "false"                     {return sym(FALSE);}
    "goto"                      {return sym(GOTO);}
    "main"                      {return sym(MAIN);}
    "true"                      {return sym(TRUE);}
    "else"                      {return sym(ELSE);}
    "and"                       {return sym(ANDC);}
    "def"                       {return sym(DEF);}
    "not"                       {return sym(NOT);}
    "!="                        {return sym(NEQ);}
    "+="                        {return sym(PLUSEQ);}
    "-="                        {return sym(MINUSEQ);}
    "->"                        {return sym(ARROW);}
    "<="                        {return sym(LTEQ);}
    "=="                        {return sym(EQEQ);}
    ">="                        {return sym(GTEQ);}
    "if"                        {return sym(IF);}
    "is"                        {return sym(IS);}
    "or"                        {return sym(ORC);}
    "~>"                        {return sym(TILARROW);}
    "!"                         {return sym(BANG);}
    "&"                         {return sym(AND);}
    "("                         {return sym(LPAREN);}
    ")"                         {return sym(RPAREN);}
    "*"                         {return sym(STAR);}
    "+"                         {return sym(PLUS);}
    ","                         {return sym(COMMA);}
    "-"                         {return sym(MINUS);}
    "."                         {return sym(DOT);}
    "/"                         {return sym(SLASH);}
    ":"                         {return sym(COLON);}
    ";"                         {return sym(SEMICOLON);}
    "<"                         {return sym(LT);}
    "="                         {return sym(EQ);}
    ">"                         {return sym(GT);}
    "@"                         {return sym(AT);}
    "["                         {return sym(LBRACKET);}
    "]"                         {return sym(RBRACKET);}
    "{"                         {return sym(LBRACE);}
    "|"                         {return sym(OR);}
    "}"                         {return sym(RBRACE);}

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
