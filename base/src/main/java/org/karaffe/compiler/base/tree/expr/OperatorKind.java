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
    DEEPEQ("===", "deepEqualsTo"),
    DEEPNOTEQ("!==", "deepNotEqualsTo"),
    NOTEQ("!=", "notEqualsTo"),
    COMMA(",", "comma"),
    RANGE("..", "range"),
    POW("**", "pow"),
    BANG("!", "bang"),
    OR("||", "or"),
    AND("&&", "and"),
    ERROR("?", "error"),
    ;

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
