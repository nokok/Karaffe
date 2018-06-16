package org.karaffe.compiler.base.tree.expr;

import org.karaffe.compiler.base.tree.AbstractTree;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.TreeKind;
import org.karaffe.compiler.base.tree.TreeVisitor;

import java.util.stream.Collectors;

public class Tuple extends AbstractTree {

    Tuple() {
        this(null);
    }

    Tuple(Tree parent) {
        super(parent, TreeKind.TUPLE);
    }

    @Override
    public <R, P> R accept(TreeVisitor<R, P> visitor, P p) {
        return visitor.visitTuple(this, p);
    }

    @Override
    public String toString() {
        return "(" + String.join(",", this.getChildren().stream().map(Object::toString).collect(Collectors.toList())) + ")";
    }
}
