package net.nokok.karaffe.javacc.ast;

public class TypeParameterId extends Identifier {

    public TypeParameterId(String name) {
        super(name);
    }

    @Override
    public Object accept(ASTVisitor visitor) {
        return visitor.onTypeParameterId(this);
    }

}
