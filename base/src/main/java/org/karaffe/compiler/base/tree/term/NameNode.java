package org.karaffe.compiler.base.tree.term;

import org.karaffe.compiler.base.tree.AbstractTree;
import org.karaffe.compiler.base.tree.TreeKind;
import org.karaffe.compiler.base.tree.TreeVisitor;

public class NameNode extends AbstractTree {

    public NameNode() {
        super(TreeKind.NAME);
    }

    @Override
    public <R, P> R accept(TreeVisitor<R, P> visitor, P p) {
        return visitor.visitNameNode(this, p);
    }

}
