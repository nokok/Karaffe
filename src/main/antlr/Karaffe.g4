grammar Karaffe;

sourceFile
  : classDef* EOF
  ;

classDef
  : CLASS Identifier nl? typeDefBody? nl?
  ;

typeDefBody
  : LBRACE nl? statement* RBRACE nl?
  ;

statement
  : entryPointBlock nl?
  | initBlock nl?
  | varDef nl?
  | assign nl?
  | expr nl?
  ;

entryPointBlock
  : ENTRYPOINT LBRACE nl? statement* RBRACE nl?
  ;

initBlock
  : INIT LBRACE nl? statement* RBRACE nl?
  ;

assign
  : target=expr '=' initializer=expr nl?
  ;

varDef
  : DEF binding ('=' initializer=expr)? nl?
  ;

binding
  : Identifier typeName nl?
  ;

typeName
  : Identifier
  ;

// Expr

expr
  : id=Identifier
  | lit=literal
  | t=THIS
  | function=expr LPAREN args=exprList? RPAREN nl?
  | left=expr right=opExpr+ nl?
  | target=expr DOT name=Identifier nl?
  | LPAREN inExpr=expr RPAREN nl?
  ;

opExpr
  : op=binaryOperator right=expr
  ;

binaryOperator
  : Identifier
  ;

exprList
  : expr (COMMA expr)*
  ;

literal
  : StringLiteral
  | IntegerLiteral
  ;

nl
  : newLine+
  ;

newLine
  : '\n'
  | ';'
  ;

ENTRYPOINT: 'entrypoint';
IMPLEMENTS: 'implements';
INTERFACE: 'interface';
NAMESPACE: 'namespace';
PROTECTED: 'protected';
ABSTRACT: 'abstract';
CONTINUE: 'continue';
DELEGATE: 'delegate';
INTERNAL: 'internal';
OVERRIDE: 'override';
DEFAULT: 'default';
EXTENDS: 'extends';
FINALLY: 'finally';
PACKAGE: 'package';
PRIVATE: 'private';
EXPORT: 'export';
EXTEND: 'extend';
IMPORT: 'import';
MODULE: 'module';
NAMEOF: 'nameof';
PUBLIC: 'public';
RETURN: 'return';
SEALED: 'sealed';
STATIC: 'static';
THROWS: 'throws';
TYPEOF: 'typeof';
ALIAS: 'alias';
ASYNC: 'async';
AWAIT: 'await';
BREAK: 'break';
CATCH: 'catch';
CLASS: 'class';
FALSE: 'false';
FINAL: 'final';
MACRO: 'macro';
MATCH: 'match';
MIXIN: 'mixin';
SUPER: 'super';
THROW: 'throw';
TRAIT: 'trait';
WHILE: 'while';
YIELD: 'yield';
CASE: 'case';
ELSE: 'else';
ENUM: 'enum';
FUNC: 'func';
INIT: 'init';
LAZY: 'lazy';
NULL: 'null';
THIS: 'this';
TRUE: 'true';
DEF: 'def';
FOR: 'for';
NEW: 'new';
TRY: 'try';
AS: 'as';
DO: 'do';
IF: 'if';
IN: 'in';
IS: 'is';
LPAREN: '(';
RPAREN: ')';
LBRACE: '{';
RBRACE: '}';
DOT: '.';
COMMA: ',';
SEMI: ';';

StringLiteral
  : '"' StringChar* '"'
  ;

IntegerLiteral
  : [1-9][0-9]*
  | '0'
  ;

fragment
StringChar
  : ~["\\\r\n]
  ;

Identifier
  : Letter LetterOrDigit*
  | OperatorChar+
  | '...'
  | '..<'
  ;

fragment
OperatorChar
  : '+'
  | '='
  | '-'
  | '*'
  | '/'
  | '%'
  | '!'
  | '~'
  | '&'
  | '>'
  | '<'
  | '^'
  | '|'
  | '#'
  | '@'
  | ':'
  ;

fragment
Letter
  : [a-zA-Z]
  ;

fragment
LetterOrDigit
  : [a-zA-Z]
  | [1-9][0-9]*
  ;

WS
  : [ \t]+ -> skip
  ;

