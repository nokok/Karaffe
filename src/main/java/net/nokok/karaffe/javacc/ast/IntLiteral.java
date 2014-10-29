package net.nokok.karaffe.javacc.ast;

public class IntLiteral extends Literal<Integer> {

    public IntLiteral(Integer value) {
        super(value);
    }

    @Override
    public Object accept(ASTVisitor visitor) {
        return visitor.onIntLiteral(this);
    }

}
