package org.karaffe.compiler.base.mir.util.attr;

import org.karaffe.compiler.base.mir.util.Label;

public class MethodInvocationAttribute extends Attribute {
    private Label methodInvocation;

    public MethodInvocationAttribute(long index) {
        this.methodInvocation = new Label("InvokingSet#" + index);
    }

    @Override
    public String toString() {
        return methodInvocation.toString();
    }
}
