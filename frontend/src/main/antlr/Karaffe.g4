grammar Karaffe;

compilationUnit 
  : topLevelStatement* EOF
  ;

topLevelStatement
  : classDef
  | statement
  ;

classDef
  : simpleClassDef
  ;

simpleClassDef
  : CLASS Identifier classDefBody?
  ;

classDefBody
  : LBRACE classDefMemberList? RBRACE
  | classDefMember
  ;

classDefMemberList
  : classDefMember classDefMemberList?
  ;

classDefMember
  : mainMethodDef
  | statement
  ;

mainMethodDef
  : MAIN statementBlock
  ;

statementBlock
  : LBRACE statementList? RBRACE
  | statement
  ;

statementList
  : statement statementList?
  ;

statement
  : exprStmt=expr
  | letStmt=LET name=Identifier typeName=Identifier (EQ initializer=expr)?
  | assignTarget=Identifier EQ expr
  | def=DEF methodName=Identifier LPAREN parameterList? RPAREN returnTypeName=Identifier statement
  | init=INIT (LPAREN parameterList RPAREN)? statement
  ;

parameterList
  : b=binding (COMMA parameterList)?
  ;

binding
  : name=Identifier typeName=Identifier
  ;

expr
  : ifExpr=IF LPAREN cond=expr RPAREN then=statement* (ELSE el=statement*)?
  | loop=WHILE LPAREN cond=expr RPAREN LBRACE body=statement* RBRACE
  | block=LBRACE statement* RBRACE
  | simple=simpleExpr
  ;

/*
  ,
  ..
  ||
  &&
  == != === !==
  <= >= < >
  + -
  * / %
  **
  ! - + is #unary operators and type check operator
  new ...
  ... as id, foo(), foo.bar(...), literal, Identifier, (...)
*/

simpleExpr
  : exprList
  ;

exprList
  : rangeExpr
  | left=exprList op=COMMA right=rangeExpr
  ;

rangeExpr
  : orExpr
  | left=rangeExpr op=(RANGE | CRANGE) right=orExpr
  ;

orExpr
  : andExpr
  | left=orExpr op=OR right=andExpr
  ;

andExpr
  : equalityExpr
  | left=andExpr op=AND right=equalityExpr
  ;

equalityExpr
  : conditionalExpr
  | left=equalityExpr op=(EQEQ | NOTEQ | EQEQEQ | NOTEQEQ) right=conditionalExpr
  ;

conditionalExpr
  : additiveExpr
  | left=conditionalExpr op=(LE | GE | LT | GT ) right=additiveExpr
  ;

additiveExpr
  : multiplicativeExpr
  | left=additiveExpr op=(PLUS | MINUS) right=multiplicativeExpr
  ;

multiplicativeExpr
  : powExpr
  | left=multiplicativeExpr op=(MUL | DIV | MOD) right=primary
  ;

powExpr
  : unaryExpr
  | left=unaryExpr op=POW right=powExpr
  ;

unaryExpr
  : primary
  | op=BANG exp=unaryExpr
  | op=PLUS exp=unaryExpr
  | op=MINUS exp=unaryExpr
  ;

primary
  : castTarget=primary AS typeName=Identifier #cast
  | methodName=Identifier LPAREN args=expr? RPAREN #localMethodInvocation
  | owner=primary DOT methodName=Identifier LPAREN args=expr? RPAREN #objectMethodInvocation
  | newInstance=NEW typeName=Identifier LPAREN args=expr? RPAREN #newInstance
  | lit=literal #lit
  | exprName=Identifier #exprName
  | nested=LPAREN expr RPAREN #nestedExpr
  ;

literal
  : IntegerLiteral
  | StringLiteral
  ;

FALLTHROUGH:  'fallthrough';

EXTENSION:    'extension';
PROTEDTED:    'protected';

ABSTRACT:     'abstract';
CONTINUE:     'continue';
INTERNAL:     'internal';
READONLY:     'readonly';

PRIVATE:      'private';

IMPORT:       'import';
PUBLIC:       'public';
RETURN:       'return';
SEALED:       'sealed';
STATIC:       'static';

ASYNC:        'async';
AWAIT:        'await';
BREAK:        'break';
CLASS:        'class';
DEFER:        'defer';
FALSE:        'false';
MATCH:        'match';
SUPER:        'super';
THROW:        'throw';
WHILE:        'while';

CASE:         'case';
ELSE:         'else';
ENUM:         'enum';
FUNC:         'func';
INIT:         'init';
MAIN:         'main';
NULL:         'null';
OPEN:         'open';
THIS:         'this';
TRUE:         'true';
TYPE:         'type';

DEF:          'def';
FOR:          'for';
LET:          'let';
NEW:          'new';
VAR:          'var';

EQEQEQ:       '===';
NOTEQEQ:      '!==';
CRANGE:       '..<';

AS:           'as';
IS:           'is';
IF:           'if';
IN:           'in';
RANGE:        '..';
POW:          '**';
AND:          '&&';
OR:           '||';
EQEQ:         '==';
NOTEQ :       '!=';
LE :          '<=';
GE :          '>=';
ARROW:        '->';
DARROW:       '=>';
RARROW:       '<-';

GT :          '>';
LT :          '<';
BANG:         '!';
COMMA:        ',';
PLUS:         '+';
MINUS:        '-';
MUL:          '*';
DIV:          '/';
MOD:          '%';
EQ:           '=';
QUOTE:        '\'';
DQUOTE:       '"';
LPAREN:       '(';
RPAREN:       ')';
LBRACE:       '{';
RBRACE:       '}';
LBRACK:       '[';
RBRACK:       ']';
SEMI:         ';';
DOT:          '.';

StringLiteral
  : DQUOTE StringChars? DQUOTE
  ;

fragment
StringChars
  : StringChar+
  ;

fragment
StringChar
  : ~["\\\r\n]
  | EscapeSeq
  ;

fragment
EscapeSeq
  : '\\' [btnfru"'\\]
  ;

IntegerLiteral
  : NonZeroDigit Digit+
  | Digit
  ;

Identifier
  : IdentifierHead IdentifierBody*
  ;

fragment IdentifierHead
  : [A-Za-z]
  ;

fragment IdentifierBody
  : IdentifierHead
  | '_'
  | [0-9]
  ;

fragment
NonZeroDigit
  : [1-9]
  ;

fragment
Digit
  : '0'
  | NonZeroDigit
  ;

Whitespace : [ \t\r\n]+ -> skip ;
