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
  Block,
  DefConstructor,
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

  StringLiteral,
  IntLiteral,

  // Reference
  TypeName,
  VarName,

  // TypedTree
  PackageName,
  ClassName,
  FieldName,
  MethodName,
  PrimitiveTypeName,

  // misc
  TAC,
  Error,
  ;
}
