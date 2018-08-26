package org.karaffe.compiler.base.tree.expr;

import org.karaffe.compiler.base.tree.AbstractTree;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.TreeKind;
import org.karaffe.compiler.base.tree.TreeVisitor;

public class Block extends AbstractTree {

    Block() {
        this(null);
    }

    Block(Tree parent) {
        super(parent, TreeKind.BLOCK);
    }

    @Override
    public <R, P> R accept(TreeVisitor<R, P> visitor, P p) {
        return visitor.visitBlock(this, p);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        this.getChildren().forEach(i -> sb.append(i).append(';'));
        sb.append("}");
        return sb.toString();
    }
}
