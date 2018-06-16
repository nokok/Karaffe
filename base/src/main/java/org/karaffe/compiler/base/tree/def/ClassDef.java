package org.karaffe.compiler.base.tree.def;

import org.karaffe.compiler.base.tree.Tree;

public class ClassDef extends AbstractDef implements Def {
    public ClassDef() {
        this(null);
    }

    public ClassDef(Tree parent) {
        super(parent, DefKind.CLASS);
    }
}
