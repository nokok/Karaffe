package net.nokok.karaffe.javacc.stmt;

import java.util.List;
import net.nokok.karaffe.javacc.identifier.TypeId;

public class FunctionType {

    private final List<TypeId> before;
    private final TypeId after;

    public FunctionType(List<TypeId> before, TypeId after) {
        this.before = before;
        this.after = after;
    }

    public int argumentSize() {
        return before.size();
    }

    public void add(TypeId type) {
        before.add(type);
    }

    public TypeId after() {
        return after;
    }
}
