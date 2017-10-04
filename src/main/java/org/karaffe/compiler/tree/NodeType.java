package org.karaffe.compiler.tree;

public enum NodeType {
    // Base
    APPLY,
    BLOCK,
    EMPTY,
    ERROR,
    NEW,

    COMPILEUNIT,
    CONSTANT,
    MODIFIER,
    LITERAL,
    EXPR,
    NAME,
    TYPENAME,
    DEFCLASS,
    DEFLABEL,
    DEFMETHOD,
    DEFPACKAGE,
    DEFVAR,
    DEFVAL,
    IF,
    PATH,
    SELECT,
    GOTO,
    ASSIGN,

    S_TYPEDEF,
    S_MODIFIER,
    S_PARAM,
    S_METHODDEF,
}
