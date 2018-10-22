package org.karaffe.compiler.base.tree.expr;

import org.karaffe.compiler.base.tree.AbstractTree;
import org.karaffe.compiler.base.tree.TreeVisitor;

public class WhileExpr extends AbstractTree {

    @Override
    public <R, P> R accept(TreeVisitor<R, P> visitor, P p) {
        return visitor.visit(this, p);
    }

}
