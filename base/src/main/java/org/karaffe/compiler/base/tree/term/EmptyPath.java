package org.karaffe.compiler.base.tree.term;

import org.karaffe.compiler.base.tree.TreeVisitor;

public class EmptyPath extends AbstractPath {

    public EmptyPath() {
        super("", "");
    }

    @Override
    public NameKind getNameKind() {
        return NameKind.NONE;
    }

    @Override
    public String asFullName() {
        return "";
    }

    @Override
    public String asSimpleName() {
        return "";
    }

    @Override
    public boolean isPrimitiveType() {
        return false;
    }

    @Override
    public boolean isResolved() {
        return false;
    }

    @Override
    public void markResolved() {
        throw new UnsupportedOperationException("EmptyPath");
    }

    @Override
    public void markUnResolved() {
        throw new UnsupportedOperationException("EmptyPath");
    }

    @Override
    public boolean isDefaultInternalName() {
        return true;
    }

    @Override
    public <P> Path accept(TreeVisitor<?, P> visitor, P p) {
        return visitor.visitEmptyName(this, p);
    }

    @Override
    public int length() {
        return 0;
    }

    @Override
    public char charAt(int i) {
        return 0;
    }

    @Override
    public CharSequence subSequence(int i, int i1) {
        throw new UnsupportedOperationException("EmptyPath");
    }
}
