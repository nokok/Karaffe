package org.karaffe.compiler.frontend.karaffe.ast.api;

public interface Statement extends TreePosition, Tree {
    public StatementType getStatementType();

}
