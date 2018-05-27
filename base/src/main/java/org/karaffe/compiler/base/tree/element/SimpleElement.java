package org.karaffe.compiler.base.tree.element;

import org.karaffe.compiler.base.tree.AbstractTree;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.TreeKind;
import org.karaffe.compiler.base.tree.TreeVisitor;

public class SimpleElement extends AbstractTree {

    public SimpleElement(TreeKind treeKind) {
        super(treeKind);
    }

    public SimpleElement(Tree parent, TreeKind treeKind) {
        super(parent, treeKind);
    }

    @Override
    public <R, P> R accept(TreeVisitor<R, P> visitor, P p) {
        switch (this.getKind()) {
        case COMPILE_UNIT:
            return visitor.visitCompileUnit(this, p);
        default:
            throw new IllegalStateException(this.getKind().toString());
        }
    }
}
