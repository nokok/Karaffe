package org.karaffe.compiler.frontend.karaffe.ast.names;

import java.util.Objects;

import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.frontend.karaffe.ast.api.AbstractTree;
import org.karaffe.compiler.frontend.karaffe.ast.api.Term;

public class SimpleName extends AbstractTree implements CharSequence, Term {
    private final String name;

    public SimpleName(String name) {
        this.name = Objects.requireNonNull(name);
        if (this.name.contains(".")) {
            throw new IllegalArgumentException();
        }
    }

    public SimpleName(SimpleName other) {
        this(other.name);
    }

    public SimpleName(Position position, String name) {
        super(position);
        this.name = Objects.requireNonNull(name);
        if (this.name.contains(".")) {
            throw new IllegalArgumentException();
        }
    }

    public SimpleName(Position position, SimpleName other) {
        this(position, other.name);
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int length() {
        return this.name.length();
    }

    @Override
    public char charAt(int index) {
        return this.name.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return this.name.subSequence(start, end);
    }

    @Override
    public boolean isNormalizable() {
        return false;
    }

    @Override
    public boolean isTermNode() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof SimpleName) {
            SimpleName other = (SimpleName) obj;
            return this.name.equals(other.name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    public static SimpleName rootClass() {
        return new SimpleName("Any");
    }

    public static SimpleName rootClass(Position position) {
        return new SimpleName(position, "Any");
    }

    public static SimpleName defaultPackageName() {
        return new SimpleName("<root>");
    }

    public static SimpleName wildCard() {
        return new SimpleName("_");
    }
}
