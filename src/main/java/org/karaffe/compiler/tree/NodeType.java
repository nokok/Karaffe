package org.karaffe.compiler.tree;

public enum NodeType {
  // AST
  CompilationUnit,
  SourceFile,
  Apply,
  Argument,
  Arguments,
  ArrayTypeName,
  Assign,
  BinOp,
  Binding,
  Body,
  Constructor,
  DefClass,
  DefMethod,
  DefVar,
  FlatApply,
  Identifier,
  Modifier,
  Modifiers,
  Module,
  Package,
  Parameter,
  Parameters,
  ReturnType,
  Select,
  SuperClass,
  This,
  TypeName,
  StringLiteral,
  IntLiteral,
  
  // misc

  Error,

  ;
}
