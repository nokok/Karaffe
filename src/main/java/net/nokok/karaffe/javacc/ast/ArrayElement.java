package net.nokok.karaffe.javacc.ast;

public class ArrayElement extends Literal<Expression<?, ?>> {

    public ArrayElement(Expression<?, ?> value) {
        super(value);
    }

    @Override
    public Object accept(ASTVisitor visitor) {
        return visitor.onArrayElement(this);
    }

}
