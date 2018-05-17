package org.karaffe.compiler.base.tree;

public interface Tree {
    <T> T accept(TreeVisitor<T> visitor);
}
