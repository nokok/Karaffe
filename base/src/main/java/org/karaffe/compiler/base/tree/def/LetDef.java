package org.karaffe.compiler.base.tree.def;

import org.karaffe.compiler.base.tree.Tree;

public class LetDef extends AbstractDef implements Def {
    public LetDef() {
        this(null);
    }

    public LetDef(Tree parent) {
        super(parent, DefKind.LET);
    }
}
