package org.karaffe.compiler.tree;

public enum NodeType {
    // SPECIAL
    NORMALIZED,
    NODELIST,

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
    RETURN,
    SELECT,
    GOTO,
    ASSIGN,

    OP_PLUS,

    S_TYPEDEF,
    S_MODIFIER,
    S_PARAM,
    S_METHODDEF,
}
