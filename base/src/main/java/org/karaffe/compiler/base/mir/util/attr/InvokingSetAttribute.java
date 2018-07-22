package org.karaffe.compiler.base.mir.util.attr;

import org.karaffe.compiler.base.mir.util.Label;

public class InvokingSetAttribute extends Attribute {
    private Label methodInvocation;

    public InvokingSetAttribute(long index) {
        this.methodInvocation = new Label("InvokingSet#" + index);
    }

    @Override
    public String toString() {
        return methodInvocation.toString();
    }
}
