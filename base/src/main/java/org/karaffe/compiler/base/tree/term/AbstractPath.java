package org.karaffe.compiler.base.tree.term;

import org.karaffe.compiler.base.pos.Position;

import java.util.Objects;

public abstract class AbstractPath implements Path {

    protected Position pos;
    protected boolean isResolved;
    protected String name;
    protected String simpleName;

    protected AbstractPath(String name) {
        this.name = Objects.requireNonNull(name);
        this.simpleName = processSimpleName(this.name);
    }

    protected AbstractPath(Path other) {
        this.name = other.asFullName();
        this.simpleName = other.asSimpleName();
    }

    protected String processSimpleName(String fullName) {
        String regex = this.delimiterRegex();
        regex = regex == null ? "\\." : regex;
        String[] split = fullName.split(regex);
        if (split.length > 1) {
            return split[split.length - 1];
        } else {
            return fullName;
        }
    }

    @Override
    public String asFullName() {
        return this.name;
    }

    @Override
    public String asSimpleName() {
        return this.simpleName;
    }

    @Override
    public Position getPos() {
        return this.pos;
    }

    @Override
    public void setPos(Position pos) {
        this.pos = Objects.requireNonNull(pos);
    }

    @Override
    public void markResolved() {
        this.isResolved = true;
    }

    @Override
    public void markUnResolved() {
        this.isResolved = false;
    }

    @Override
    public boolean isResolved() {
        return this.isResolved;
    }

    @Override
    public int length() {
        return this.name.length();
    }

    @Override
    public char charAt(int i) {
        return this.name.charAt(i);
    }

    @Override
    public CharSequence subSequence(int i, int i1) {
        return this.name.subSequence(i, i1);
    }

    @Override
    public String toString() {
        return this.asFullName();
    }
}
