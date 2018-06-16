package org.karaffe.compiler.base.tree.expr;

import org.karaffe.compiler.base.tree.TreeVisitor;
import org.karaffe.compiler.base.tree.term.NameKind;
import org.karaffe.compiler.base.tree.term.Path;
import org.karaffe.compiler.base.tree.term.SimplePath;

import java.util.Objects;

public class SimpleOperator extends SimplePath implements Operator {

    private OperatorKind operatorKind;

    SimpleOperator(OperatorKind operatorKind) {
        super(operatorKind.getFullName(), NameKind.OPERATOR);
        this.operatorKind = Objects.requireNonNull(operatorKind);
    }

    @Override
    public <P> Path accept(TreeVisitor<?, P> visitor, P p) {
        return visitor.visitOperator(this, p);
    }

    @Override
    public OperatorKind getOperatorKind() {
        return this.operatorKind;
    }

    @Override
    public void setOperatorKind(OperatorKind operatorKind) {
        this.operatorKind = Objects.requireNonNull(operatorKind);
    }

    public NameKind getNameKind() {
        return NameKind.OPERATOR;
    }

    public String asFullName() {
        return this.operatorKind.getFullName();
    }

    public String asSimpleName() {
        return this.operatorKind.getSimpleName();
    }

}
