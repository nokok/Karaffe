package org.karaffe.compiler.base.tree.expr;

import org.karaffe.compiler.base.tree.AbstractTree;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.TreeKind;
import org.karaffe.compiler.base.tree.TreeVisitor;

public class Binding extends AbstractTree {

    public Binding(Tree parent) {
        super(parent, TreeKind.BINDING);
    }

    @Override
    public <R, P> R accept(TreeVisitor<R, P> visitor, P p) {
        return visitor.visitBinding(this, p);
    }
}
