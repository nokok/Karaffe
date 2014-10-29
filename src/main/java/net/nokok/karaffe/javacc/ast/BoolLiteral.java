package net.nokok.karaffe.javacc.ast;

public class BoolLiteral extends Literal<Boolean> {

    public BoolLiteral(Boolean value) {
        super(value);
    }

    @Override
    public Object accept(ASTVisitor visitor) {
        return visitor.onBoolLiteral(this);
    }

}
