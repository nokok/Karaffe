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
%cupdebug

%line
%column

%{
    private Symbol sym(int type) {
        return new Symbol(type, yyline + 1, yycolumn + 1);
    }
    
    private Symbol sym(int type, Object value) {
        return new Symbol(type, yyline + 1, yycolumn + 1, value);
    }

    public class SymInfo {
        private final int line;
        private final int column;
        private final java.util.Optional<?> value;

        public SymInfo() {
            this(null);
        }

        public SymInfo(Object value) {
            this(yyline,yycolumn,value);
        }

        public SymInfo(int line,int column) {
            this(line,column,null);
        }

        public SymInfo(int line, int column, Object value) {
            this.line = line;
            this.column = column;
            this.value = java.util.Optional.ofNullable(value);
        }

        public int line() {
            return line;
        }

        public int column() {
            return column;
        }

        public java.util.Optional<?> value() {
            return value;
        }

        @Override public String toString() {
            return "[line:" + line + ", column:" + column + "] = " + value;
        }
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
    "package"                   {return sym(PACKAGE, new SymInfo());}
    "."                         {return sym(DOT, new SymInfo());}

    {Identifier}                {return sym(IDENTIFIER, new SymInfo(yytext()));}
    {WhiteSpace}                {}
    {Comment}                   {}

}

<<EOF>> {
    return sym(EOF);
}
