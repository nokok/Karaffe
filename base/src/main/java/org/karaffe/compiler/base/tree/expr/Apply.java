package org.karaffe.compiler.base.tree.expr;

import org.karaffe.compiler.base.tree.AbstractTree;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.TreeVisitor;

public class Apply extends AbstractTree {

    @Override
    public <R, P> R accept(TreeVisitor<R, P> visitor, P p) {
        return visitor.visit(this, p);
    }

    public Tree getTarget() {
        return this.getChild(0);
    }

    public Tree getArgs() {
        return this.getChild(1);
    }
}
