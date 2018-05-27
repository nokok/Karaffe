package org.karaffe.compiler.base.tree.expr;

import org.karaffe.compiler.base.tree.AbstractTree;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.TreeKind;
import org.karaffe.compiler.base.tree.TreeVisitor;

import java.util.Objects;

public class AbstractExpr extends AbstractTree implements Expr {

    private ExprKind exprKind;

    public AbstractExpr(ExprKind exprKind) {
        this(null, exprKind);
    }

    public AbstractExpr(Tree parent, ExprKind exprKind) {
        super(parent, TreeKind.EXPR);
        this.exprKind = Objects.requireNonNull(exprKind);
    }

    @Override
    public <R, P> R accept(TreeVisitor<R, P> visitor, P p) {
        return visitor.visit(this, p);
    }
}
