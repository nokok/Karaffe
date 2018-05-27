package org.karaffe.compiler.base.tree.expr;

import org.karaffe.compiler.base.tree.AbstractTree;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.TreeKind;
import org.karaffe.compiler.base.tree.TreeVisitor;

import java.util.Objects;

public class SimpleOperator extends AbstractTree implements Operator {

    private OperatorKind operatorKind;

    public SimpleOperator(OperatorKind operatorKind) {
        this(null, operatorKind);
    }

    public SimpleOperator(Tree parent, OperatorKind operatorKind) {
        super(parent, TreeKind.NAME);
        this.operatorKind = Objects.requireNonNull(operatorKind);
    }

    @Override
    public <R, P> R accept(TreeVisitor<R, P> visitor, P p) {
        return null;
    }

    @Override
    public OperatorKind getOperatorKind() {
        return this.operatorKind;
    }

    @Override
    public void setOperatorKind(OperatorKind operatorKind) {
        this.operatorKind = Objects.requireNonNull(operatorKind);
    }
}
