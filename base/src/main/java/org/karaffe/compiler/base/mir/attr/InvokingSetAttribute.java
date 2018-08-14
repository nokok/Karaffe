package org.karaffe.compiler.base.mir.attr;

import org.karaffe.compiler.base.attr.Attribute;
import org.karaffe.compiler.base.mir.util.Label;

public class InvokingSetAttribute extends Attribute {
    private Label methodInvocation;

    public InvokingSetAttribute(long index) {
        this.methodInvocation = new Label("InvokingSet#" + index);
    }

    public Label getLabel() {
        return methodInvocation;
    }

    @Override
    public String toString() {
        return methodInvocation.toString();
    }
}
