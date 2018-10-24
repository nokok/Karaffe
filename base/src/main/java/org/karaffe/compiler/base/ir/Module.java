package org.karaffe.compiler.base.ir;

public class Module extends AbstractElement {
    @Override
    public <R> R accept(IRVisitor<R> visitor) {
        return visitor.visit(this);
    }

}
