package org.karaffe.compiler.base.tree.expr;

import org.karaffe.compiler.base.tree.term.Path;

public interface Operator extends Path {
    OperatorKind getOperatorKind();

    void setOperatorKind(OperatorKind operatorKind);
}
