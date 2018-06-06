package org.karaffe.compiler.base.tree.expr;

import org.karaffe.compiler.base.tree.AbstractTree;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.TreeKind;
import org.karaffe.compiler.base.tree.TreeVisitor;

import java.util.Objects;

public class Atom extends AbstractTree {

    private AtomKind kind;
    private String value;

    Atom(AtomKind kind) {
        this(null, kind);
    }

    Atom(Tree parent, AtomKind kind) {
        super(parent, TreeKind.ATOM);
        this.kind = Objects.requireNonNull(kind);
        this.value = "";
    }

    public void setValue(String value) {
        this.value = Objects.requireNonNull(value);
    }

    public String getValue() {
        return value;
    }

    @Override
    public <R, P> R accept(TreeVisitor<R, P> visitor, P p) {
        return visitor.visitAtom(this, p);
    }
}
