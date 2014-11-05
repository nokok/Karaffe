package net.nokok.karaffe.javacc.ast;

import net.nokok.karaffe.javacc.excptn.EmptyIdentifierException;

public class VariableId extends Identifier {

    public VariableId(String name) {
        super(name);
        if ( name.isEmpty() ) {
            throw new EmptyIdentifierException();
        }
    }

    @Override
    public Object accept(ASTVisitor visitor) {
        return visitor.onVaraibleId(this);
    }

    @Override
    public String nodeIdentifier() {
        return "VariableId";
    }

}
