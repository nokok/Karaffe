package org.karaffe.compiler.base.ir;

import org.karaffe.compiler.base.attr.NameAttribute;

public class Class extends AbstractElement {

    @Override
    public <R> R accept(IRVisitor<R> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return "class " + this.getAttribute(NameAttribute.class).map(NameAttribute::getName).orElse("<empty>");
    }
}
