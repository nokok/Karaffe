package org.karaffe.compiler.base.tree.expr;

public enum OperatorKind {
    PLUS("+", "plus"),
    MINUS("-", "minus"),
    MUL("*", "mul"),
    DIV("/", "div"),
    MOD("%", "mod"),
    LT("<", "lessThan"),
    GT(">", "greaterThan"),
    LE("<=", "lessThanEquals"),
    GE(">=", "greaterThanEquals"),
    EQEQ("==", "equalsTo"),
    NOTEQ("!=", "notEqualsTo"),
    ERROR("?", "error"),;

    private String simpleName;
    private String fullName;

    OperatorKind(String simpleName, String fullName) {
        this.simpleName = simpleName;
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getSimpleName() {
        return simpleName;
    }
}
