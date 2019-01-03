package org.karaffe.compiler.tree;

public enum NodeType {
    CompilationUnit,
    SourceFile,
    DefMethod,
    DefVar,
    Apply,
    Select,
    IntLiteral,
    StringLiteral,
    Error,
    This,
    DefClass,
    Modifier,
    SuperClass,
    ReturnType,
    Parameters,
    Parameter,
    Modifiers,
    TypeName,
    Identifier,
    Body,
    Arguments,
    Argument
}