package org.karaffe.compiler.base.tree.def;

import org.karaffe.compiler.base.tree.Tree;

public class MethodDef extends AbstractDef implements Def {
    public MethodDef() {
        this(null);
    }

    public MethodDef(Tree parent) {
        super(parent, DefKind.METHOD);
    }
}
