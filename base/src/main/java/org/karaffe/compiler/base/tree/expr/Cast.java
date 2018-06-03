package org.karaffe.compiler.base.tree.expr;

import org.karaffe.compiler.base.tree.AbstractTree;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.TreeKind;
import org.karaffe.compiler.base.tree.TreeVisitor;

public class Cast extends AbstractTree {

    public Cast() {
        this(null);
    }

    public Cast(Tree parent) {
        super(parent, TreeKind.CAST);
    }

    @Override
    public <R, P> R accept(TreeVisitor<R, P> visitor, P p) {
        return visitor.visitCast(this, p);
    }
}
