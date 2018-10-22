package org.karaffe.compiler.base.tree.def;

import org.karaffe.compiler.base.tree.TreeVisitor;

public class AssignmentDef extends AbstractDef implements Def {

    @Override
    public String toString() {
        return getName() + " = " + getChild(0);
    }

    @Override
    public <R, P> R accept(TreeVisitor<R, P> visitor, P p) {
        return visitor.visit(this, p);
    }
}
