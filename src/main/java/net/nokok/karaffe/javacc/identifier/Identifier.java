package net.nokok.karaffe.javacc.identifier;

import net.nokok.karaffe.javacc.Name;

public class Identifier {

    protected final Name name;

    public Identifier(Name name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name.toString();
    }
}
