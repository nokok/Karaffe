package net.nokok.karaffe.javacc.ast;

public class KaraffeTypeLiteral extends Literal<Object> {

    public KaraffeTypeLiteral(Object value) {
        super(value);
    }

    @Override
    public Object accept(ASTVisitor visitor) {
        return visitor.onKaraffeTypeLiteral(this);
    }

    @Override
    public String nodeIdentifier() {
        return "KaraffeTypeLiteral";
    }

}
