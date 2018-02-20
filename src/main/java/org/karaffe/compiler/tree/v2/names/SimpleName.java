package org.karaffe.compiler.tree.v2.names;

import org.karaffe.compiler.pos.Position;
import org.karaffe.compiler.tree.v2.api.AbstractTree;

public class SimpleName extends AbstractTree implements CharSequence {
    private final String name;

    public SimpleName(String name) {
        this.name = name;
    }

    public SimpleName(SimpleName other) {
        this.name = other.name;
    }

    public SimpleName(Position position, String name) {
        super(position);
        this.name = name;
    }

    public SimpleName(Position position, SimpleName other) {
        super(position);
        this.name = other.name;
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

}
