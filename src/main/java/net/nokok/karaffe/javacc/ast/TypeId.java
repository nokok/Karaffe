package net.nokok.karaffe.javacc.ast;

public class TypeId extends Identifier implements ASTNode {

    public static final TypeId UNKNOWN_TYPE = new TypeId("[NEED_TYPE_INFERENCE]");

    public TypeId(String name) {
        super(name);
    }

    @Override
    public Object accept(ASTVisitor visitor) {
        return visitor.onTypeId(this);
    }

    @Override
    public String nodeIdentifier() {
        return "TypeId";
    }

}
