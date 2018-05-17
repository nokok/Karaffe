package org.karaffe.compiler.base.tree;

public interface NamedElement<T> {
    public void setName(T name);

    public T getName();
}
