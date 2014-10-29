package net.nokok.karaffe.javacc.ast;

public class StringLiteral extends Literal<String> {

    public StringLiteral(String value) {
        super(value);
    }

    @Override
    public Object accept(ASTVisitor visitor) {
        return visitor.onStringLiteral(this);
    }

}
