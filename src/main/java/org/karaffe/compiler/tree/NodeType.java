package org.karaffe.compiler.tree;

public enum NodeType {
    // Base
    APPLY,
    BLOCK,
    EMPTY,
    ERROR,

    COMPILEUNIT,
    CONSTANT,
    MODIFIER,
    LITERAL,
    VARNAME,
    TYPENAME,
    DEFCLASS,
    DEFMETHOD,
    DEFPACKAGE,
    DEFVAR,
    DEFVAL,
    PATH,
    SELECT,

    S_TYPEDEF,
    S_MODIFIER,
    S_PARAM,
    S_METHODDEF,
}
