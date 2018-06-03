package org.karaffe.compiler.base.tree.expr;

import org.karaffe.compiler.base.tree.AbstractTree;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.TreeKind;
import org.karaffe.compiler.base.tree.TreeVisitor;

public class WhileExpr extends AbstractTree {

    public WhileExpr() {
        this(null);
    }

    public WhileExpr(Tree parent) {
        super(parent, TreeKind.WHILEEXPR);
    }

    @Override
    public <R, P> R accept(TreeVisitor<R, P> visitor, P p) {
        return visitor.visitWhileExpr(this, p);
    }
}
