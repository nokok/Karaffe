package org.karaffe.compiler.base.tree.element;

import org.karaffe.compiler.base.tree.AbstractTree;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.TreeKind;
import org.karaffe.compiler.base.tree.TreeVisitor;
import org.karaffe.compiler.base.tree.type.Type;

public class MethodDef extends AbstractTree {

    public MethodDef(Tree parent) {
        super(parent, TreeKind.METHOD_DEF);
    }

    public void setReturnType(Type type) {
        this.setOrReplaceChild(0, type);
    }

    @Override
    public <R, P> R accept(TreeVisitor<R, P> visitor, P p) {
        return visitor.visit(this, p);
    }
}
