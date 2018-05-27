package org.karaffe.compiler.base.tree.expr;

import org.karaffe.compiler.base.tree.Tree;

public interface Operator extends Tree {
    OperatorKind getOperatorKind();

    void setOperatorKind(OperatorKind operatorKind);
}
