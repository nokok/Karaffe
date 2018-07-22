package org.karaffe.compiler.base.tree;

public enum TreeKind {
    /* Top Level Elements */
    DEF,
    NAME,
    MODIFIER,
    TYPE,

    /* General Elements */
    COMPILE_UNIT,
    TEMPLATE,
    APPLY,
    ATOM,
    BLOCK,
    SELECT,
    TUPLE,
    EMPTY,
    IFEXPR,
    WHILEEXPR,
    CAST,
    BINDING,
    RETURN;
}
