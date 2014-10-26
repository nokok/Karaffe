package net.nokok.karaffe.javacc.identifier;

import net.nokok.karaffe.javacc.Name;
import net.nokok.karaffe.javacc.Token;

public class VariableId extends Identifier {

    public VariableId(Name name) {
        super(name);
    }

    public VariableId(String name) {
        super(new Name(name));
    }

    public VariableId(Token token) {
        this(token.image);
    }

}
