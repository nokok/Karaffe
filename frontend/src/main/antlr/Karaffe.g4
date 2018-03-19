grammar Karaffe;

compilationUnit 
  : statement*
  ;

statement 
  : intLiteral
  ;

intLiteral
  : IntegerLiteral
  ;

IntegerLiteral
  : NonZeroDigit Digit*
  | Digit
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
