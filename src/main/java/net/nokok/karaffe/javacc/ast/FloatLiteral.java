package net.nokok.karaffe.javacc.ast;

public class FloatLiteral extends Literal<Double> {

    public FloatLiteral(Double value) {
        super(value);
    }

    @Override
    public Object accept(ASTVisitor visitor) {
        return visitor.onFloatLiteral(this);
    }

    @Override
    public String nodeIdentifier() {
        return "FloatLiteral";
    }

}
