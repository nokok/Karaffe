package org.karaffe.compiler.ast.api;

public interface Statement extends TreePosition, Tree {
    public StatementType getStatementType();

}
