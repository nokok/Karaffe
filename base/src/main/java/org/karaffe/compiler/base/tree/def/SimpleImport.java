package org.karaffe.compiler.base.tree.def;

import org.karaffe.compiler.base.tree.Tree;

public class SimpleImport extends AbstractDef implements Def {
    public SimpleImport() {
        this(null);
    }

    public SimpleImport(Tree parent) {
        super(parent, DefKind.SIMPLE_IMPORT);
    }

    @Override
    public String toString() {
        return "import " + this.getName();
    }
}
