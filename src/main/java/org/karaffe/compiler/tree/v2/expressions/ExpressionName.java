package org.karaffe.compiler.tree.v2.expressions;

import org.karaffe.compiler.pos.Position;
import org.karaffe.compiler.tree.v2.api.AbstractTree;
import org.karaffe.compiler.tree.v2.api.Expression;

public class ExpressionName extends AbstractTree implements Expression, CharSequence {
    private final String name;

    public ExpressionName(String name) {
        this.name = name;
    }

    public ExpressionName(Position position, String name) {
        super(position);
        this.name = name;
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
