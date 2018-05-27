package org.karaffe.compiler.base.tree.def;

import org.karaffe.compiler.base.tree.AbstractTree;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.TreeKind;
import org.karaffe.compiler.base.tree.TreeVisitor;

public class SimpleDef extends AbstractTree implements Def {

    private DefKind defKind;

    public SimpleDef(DefKind defKind) {
        this(null, defKind);
    }

    public SimpleDef(Tree parent, DefKind defKind) {
        super(parent, TreeKind.DEF);
    }

    @Override
    public <R, P> R accept(TreeVisitor<R, P> visitor, P p) {
        return visitor.visit(this, p);
    }

    @Override
    public void addBody(Tree body) {
        this.addChild(body);
    }
}
