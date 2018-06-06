package org.karaffe.compiler.base.tree.term;

import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.TreeKind;

import java.util.List;

public interface Name extends CharSequence, Tree, Term {
    NameType getType();

    @Override
    default void setKind(TreeKind kind) {
        throw new UnsupportedOperationException();
    }

    @Override
    default List<Tree> getModifiers() {
        throw new UnsupportedOperationException();
    }

    @Override
    default void addModifier(Tree modifier) {
        throw new UnsupportedOperationException();
    }

    @Override
    default void clearModifiers() {
        throw new UnsupportedOperationException();
    }

    @Override
    default Tree getName() {
        throw new UnsupportedOperationException();
    }

    @Override
    default void setName(Tree name) {
        throw new UnsupportedOperationException();
    }

    @Override
    default void addChild(Tree child) {
        throw new UnsupportedOperationException();
    }

    @Override
    default void setOrReplaceChild(int index, Tree child) {
        throw new UnsupportedOperationException();
    }

    @Override
    default List<Tree> getChildren() {
        throw new UnsupportedOperationException();
    }

}
