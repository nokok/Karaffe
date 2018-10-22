package org.karaffe.compiler.base.tree.def;

import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.TreeVisitor;

public class LetDef extends AbstractDef implements Def {

    public Tree getInitializer() {
        return this.getChild(0);
    }

    @Override
    public <R, P> R accept(TreeVisitor<R, P> visitor, P p) {
        return visitor.visit(this, p);
    }
}
