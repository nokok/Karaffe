package org.karaffe.compiler.base.tree;

public interface Tree {
    <R, P> R accept(TreeVisitor<R, P> visitor, P p);
}
