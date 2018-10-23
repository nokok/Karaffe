package org.karaffe.compiler.base.tree.expr.op;

import org.karaffe.compiler.base.tree.AbstractTree;
import org.karaffe.compiler.base.tree.expr.Operator;

public abstract class AbstractOperator extends AbstractTree implements Operator {

    @Override
    public int length() {
        return this.getName().toString().length();
    }

    @Override
    public char charAt(int i) {
        return this.getName().toString().charAt(i);
    }

    @Override
    public CharSequence subSequence(int i, int i1) {
        return this.getName().toString().substring(i, i1);
    }
}
