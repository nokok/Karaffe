package org.karaffe.compiler.base.tree;

public class Source extends AbstractTree {

    public Source() {
        super(TreeKind.SOURCE);
    }

    @Override
    public <R, P> R accept(TreeVisitor<R, P> visitor, P p) {
        return visitor.visit(this, p);
    }
}
