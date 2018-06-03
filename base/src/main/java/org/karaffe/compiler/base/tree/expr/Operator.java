package org.karaffe.compiler.base.tree.expr;

import org.karaffe.compiler.base.tree.term.Name;

public interface Operator extends Name {
    OperatorKind getOperatorKind();

    void setOperatorKind(OperatorKind operatorKind);
}
