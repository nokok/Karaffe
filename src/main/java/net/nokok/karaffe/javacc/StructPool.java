package net.nokok.karaffe.javacc;

import java.util.ArrayList;
import java.util.List;

public class StructPool {

    private final List<Struct> types = new ArrayList<>();

    public void addType(String typeName) {
        if ( hasType(typeName) ) {
            throw new RuntimeException("Duplicate type");
        }
        types.add(new Struct(new Name(typeName)));
    }

    public void addFunction(Struct s, Function f) {
        s.addFunction(f);
        if ( types.contains(s) ) {
            types.set(types.indexOf(s), s);
        } else {
            types.add(s);
        }
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
