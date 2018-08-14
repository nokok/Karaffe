package org.karaffe.compiler.backend.jvm.attr;

import net.nokok.azm.Label;
import org.karaffe.compiler.base.attr.Attribute;

import java.util.Objects;

public class JavaVMScopeAttribute extends Attribute {
    private Label begin;
    private Label end;

    public JavaVMScopeAttribute(Label begin, Label end) {
        this.begin = Objects.requireNonNull(begin);
        this.end = Objects.requireNonNull(end);
    }

    public Label getBegin() {
        return begin;
    }

    public Label getEnd() {
        return end;
    }
}
