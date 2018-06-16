package org.karaffe.compiler.base.tree.def;

import org.karaffe.compiler.base.tree.Tree;

public class OnDemandImport extends AbstractDef implements Def {
    public OnDemandImport() {
        this(null);
    }

    public OnDemandImport(Tree parent) {
        super(parent, DefKind.ONDEMAND_IMPORT);
    }

    @Override
    public String toString() {
        return "import " + this.getName() + ".*";
    }
}
