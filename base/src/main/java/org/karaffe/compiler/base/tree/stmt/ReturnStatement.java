package org.karaffe.compiler.base.tree.stmt;

import org.karaffe.compiler.base.tree.AbstractTree;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.TreeVisitor;

public class ReturnStatement extends AbstractTree {

    @Override
    public <R, P> R accept(TreeVisitor<R, P> visitor, P p) {
        return visitor.visit(this, p);
    }

    public Tree returnObj() {
        return this.getChild(0);
    }

    @Override
    public String toString() {
        return "return " + returnObj();
    }
}
