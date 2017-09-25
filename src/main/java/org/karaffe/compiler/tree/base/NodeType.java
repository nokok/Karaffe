package org.karaffe.compiler.tree.base;

public enum NodeType {
    // Applying
    APPLY,

    // Trees
    COMPILEUNIT,
    EMPTY,
    ERROR,

    // Terms
    CONSTANT,
    MODIFIER,
    LITERAL,
    VARNAME,
    TYPENAME,

    // Defs
    DEFCLASS,
    DEFMETHOD,
    DEFPACKAGE,
    DEFVAR,
    DEFVAL,

    // Selector
    PATH,
    SELECT,

    // Collections
    S_TYPEDEF,
    S_MODIFIER,
    S_PARAM,
}
