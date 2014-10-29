package net.nokok.karaffe.javacc.ast;

public class UndefinedLiteral extends Literal<UndefinedValue> {

    public UndefinedLiteral() {
        super(UndefinedValue.VALUE);
    }

    @Override
    public Object accept(ASTVisitor visitor) {
        return visitor.onUndefinedLiteral(this);
    }

}
