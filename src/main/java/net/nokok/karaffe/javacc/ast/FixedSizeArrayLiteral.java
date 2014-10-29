package net.nokok.karaffe.javacc.ast;

public class FixedSizeArrayLiteral extends Literal<Object[]> {

    public FixedSizeArrayLiteral(Object[] value) {
        super(value);
    }

    @Override
    public Object accept(ASTVisitor visitor) {
        return visitor.onFixedSizeArrayLiteral(this);
    }

}
