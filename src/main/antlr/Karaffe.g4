grammar Karaffe;

program
 : mainClass classDecl* EOF
 ;

mainClass
 : 'class' Identifier '{' 'public' 'static' 'void' 'main' '(' 'String' '[]' Identifier ')' '{' statement '}' '}'
 ;

classDecl
 : 'class' Identifier '{' varDecl* methodDecl* '}'
 | 'class' Identifier 'extends' Identifier '{' varDecl* methodDecl* '}'
 ;

varDecl
 : type Identifier ';'
 ;

methodDecl
 : 'public' type Identifier '(' formalList ')' '{' varDecl* statement* 'return' exp ';' '}'
 ;

formalList
 : (type Identifier formalRest*)?
 ;

formalRest
 : ',' type Identifier
 ;

type
 : 'int' '[]'
 | 'boolean'
 | 'int'
 | Identifier
 ;

statement
 : '{' statement* '}'
 | 'if' '(' exp ')' statement 'else' statement
 | 'while' '(' exp ')' statement
 | 'System.out.println' '(' exp ')' ';'
 | Identifier '=' exp ';'
 | Identifier '[' exp ']' '=' exp ';'
 ;

exp
 : exp Operator exp
 | exp '[' exp ']'
 | exp '.' 'length'
 | exp '.' Identifier '(' expList ')'
 | IntegerLiteral
 | 'true'
 | 'false'
 | Identifier
 | 'this'
 | 'new' 'int' '[' exp ']'
 | 'new' Identifier '(' ')'
 | '!' exp
 | '(' exp ')'
 ;

expList
 : (exp expRest*)?
 ;

expRest
 : ',' exp
 ;

IntegerLiteral
 : Digit
 ;
 
Identifier
 : Letter+
 ;

Digit
 : NonZeroDigit ('0' .. '9')*
 ;

fragment NonZeroDigit
 : '1' .. '9'
 ;

Operator
 : '&&'
 | '<'
 | '+'
 | '-'
 | '*'
 ;

fragment Letter
 : 'a' .. 'z'
 | 'A' .. 'Z'
 ;

