package org.karaffe.compiler.base.tree.expr;

import org.karaffe.compiler.base.tree.AbstractTree;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.TreeKind;
import org.karaffe.compiler.base.tree.TreeVisitor;
import org.karaffe.compiler.base.tree.term.NameType;

import java.util.Objects;

public class SimpleOperator extends AbstractTree implements Operator {

    private OperatorKind operatorKind;

    SimpleOperator(OperatorKind operatorKind) {
        this(null, operatorKind);
    }

    SimpleOperator(Tree parent, OperatorKind operatorKind) {
        super(parent, TreeKind.NAME);
        this.operatorKind = Objects.requireNonNull(operatorKind);
    }

    @Override
    public <R, P> R accept(TreeVisitor<R, P> visitor, P p) {
        return visitor.visitOperator((Operator)this, p);
    }

    @Override
    public OperatorKind getOperatorKind() {
        return this.operatorKind;
    }

    @Override
    public void setOperatorKind(OperatorKind operatorKind) {
        this.operatorKind = Objects.requireNonNull(operatorKind);
    }

    @Override
    public NameType getType() {
        return NameType.OPERATOR;
    }

    @Override
    public int length() {
        return 1;
    }

    @Override
    public char charAt(int i) {
        return 0;
    }

    @Override
    public CharSequence subSequence(int i, int i1) {
        return null;
    }
}
