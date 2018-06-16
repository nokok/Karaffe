package org.karaffe.compiler.base.tree.def;

import org.karaffe.compiler.base.tree.Tree;

public class AssignmentDef extends AbstractDef implements Def {
    public AssignmentDef() {
        this(null);
    }

    public AssignmentDef(Tree parent) {
        super(parent, DefKind.ASSIGNMENT);
    }

    @Override
    public String toString() {
        return getName() + " = " + getChild(0);
    }
}
