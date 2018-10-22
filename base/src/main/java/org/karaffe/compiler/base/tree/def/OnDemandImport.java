package org.karaffe.compiler.base.tree.def;

import org.karaffe.compiler.base.tree.TreeVisitor;

public class OnDemandImport extends AbstractDef implements Def {

    @Override
    public String toString() {
        return "import " + this.getName() + ".*";
    }

    @Override
    public <R, P> R accept(TreeVisitor<R, P> visitor, P p) {
        return visitor.visit(this, p);
    }
}
