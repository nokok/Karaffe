package net.nokok.karaffe.javacc.ast;

public class VariableId extends Identifier {

    public VariableId(String name) {
        super(name);
        if ( Character.isUpperCase(name.charAt(0)) ) {
            throw new IllegalArgumentException("VariableId:[" + name + "]");
        }
        if ( name.isEmpty() ) {
            throw new IllegalArgumentException("Empty Identifier");
        }
    }

    @Override
    public Object accept(ASTVisitor visitor) {
        return visitor.onVaraibleId(this);
    }

}
