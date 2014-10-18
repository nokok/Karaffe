package net.nokok.karaffe.javacc;

import java.util.ArrayList;
import java.util.List;

public class TypePool {

    private final List<Type> types = new ArrayList<>();

    public void addType(Token token) {
        addType(token.image);
    }

    public void addType(String typeName) {
        if ( hasType(typeName) ) {
            throw new RuntimeException("Duplicate type");
        }
        types.add(new Type(new Name(typeName)));
    }

    public boolean hasType(Token token) {
        return hasType(token.image);
    }

    public boolean hasType(String typeName) {
        return types
            .stream()
            .map(t -> t.getName())
            .filter(t -> t.equals(typeName))
            .findAny()
            .isPresent();
    }

    public int typeCount() {
        return types.size();
    }
}
