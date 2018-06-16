package org.karaffe.compiler.base.tree.def;

import org.karaffe.compiler.base.tree.Tree;

public class PackageDef extends AbstractDef implements Def {
    public PackageDef(Tree parent) {
        super(parent, DefKind.PACKAGE);
    }

    @Override
    public String toString() {
        return "package " + this.getName();
    }
}
