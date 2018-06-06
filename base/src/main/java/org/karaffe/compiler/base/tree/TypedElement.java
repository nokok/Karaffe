package org.karaffe.compiler.base.tree;

public interface TypedElement {
    Tree asType();

    void setType(Tree type);
}
