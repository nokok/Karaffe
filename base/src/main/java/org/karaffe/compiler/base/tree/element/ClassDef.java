package org.karaffe.compiler.base.tree.element;

import org.karaffe.compiler.base.tree.AbstractTree;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.TreeKind;
import org.karaffe.compiler.base.tree.TreeVisitor;

public class ClassDef extends AbstractTree implements Def {

    public ClassDef(Tree parent) {
        super(parent, TreeKind.CLASS_DEF);
    }

    @Override
    public <R, P> R accept(TreeVisitor<R, P> visitor, P p) {
        return visitor.visit(this, p);
    }
}
