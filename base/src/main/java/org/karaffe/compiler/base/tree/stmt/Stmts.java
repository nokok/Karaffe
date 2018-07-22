package org.karaffe.compiler.base.tree.stmt;

import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.term.Terms;

public interface Stmts {
    static Tree returnStmt(Position position) {
        return returnStmt(position, Terms.emptyTree());
    }

    public static Tree returnStmt(Position position, Tree returnTree) {
        ReturnStatement returnStatement = new ReturnStatement();
        returnStatement.setPos(position);
        returnStatement.addChild(returnTree);
        return returnStatement;
    }
}
