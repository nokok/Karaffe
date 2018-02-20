package org.karaffe.compiler.tree.v2.api;

public interface Expression extends Statement {
    @Override
    public default StatementType getStatementType() {
        return StatementType.EXPRESSION;
    }
}
