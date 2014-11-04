package net.nokok.karaffe.javacc.ast;

public class TypeParameters extends Statement {

    @Override
    public Object accept(ASTVisitor visitor) {
        return visitor.onTypeParameters(this);
    }

    @Override
    public String nodeIdentifier() {
        return "TypeParameters";
    }

}
