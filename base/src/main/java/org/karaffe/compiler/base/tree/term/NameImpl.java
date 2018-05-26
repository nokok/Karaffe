package org.karaffe.compiler.base.tree.term;

import org.karaffe.compiler.base.tree.TreeVisitor;

import java.util.Objects;

class NameImpl implements Name {

    static final NameImpl EMPTY_NAME = new NameImpl("__EMPTY__", NameType.NONE);
    private final NameType nameType;
    private final String name;

    NameImpl(String name, NameType nameType) {
        this.nameType = Objects.requireNonNull(nameType);
        this.name = Objects.requireNonNull(name);
    }

    @Override
    public int length() {
        return name.length();
    }

    @Override
    public char charAt(int i) {
        return name.charAt(i);
    }

    @Override
    public CharSequence subSequence(int i, int i1) {
        return name.subSequence(i, i1);
    }

    @Override
    public <R, P> R accept(TreeVisitor<R, P> visitor, P p) {
        return visitor.visit(this, p);
    }

    @Override
    public NameType getType() {
        return nameType;
    }
}
