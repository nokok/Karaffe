package org.karaffe.compiler.base.tree.term;

import org.karaffe.compiler.base.tree.LocatableElement;
import org.karaffe.compiler.base.tree.TreeVisitor;

public interface Path extends CharSequence, Term, LocatableElement {
    NameKind getNameKind();

    String asFullName();

    String asSimpleName();

    String delimiterRegex();

    boolean isPrimitiveType();

    default boolean isVoidType() {
        return false;
    }

    boolean isResolved();

    void markResolved();

    void markUnResolved();

    boolean isDefaultInternalName();

    <P> Path accept(TreeVisitor<?, P> visitor, P p);
}
