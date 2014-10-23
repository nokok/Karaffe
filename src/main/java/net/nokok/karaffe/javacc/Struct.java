package net.nokok.karaffe.javacc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Struct {

    private final Name typeName;
    private Optional<Name> baseTypeName = Optional.empty();
    private final List<Function> functions = new ArrayList<>();
    private final List<Variable> variables = new ArrayList<>();

    public Struct(Name name) {
        this.typeName = name;
    }

    public Struct(Name typeName, Name baseTypeName) {
        this(typeName);
        this.baseTypeName = Optional.of(baseTypeName);
    }

    public String getName() {
        return typeName.toString();
    }

    public void addFunction(Function f) {
        functions.add(f);
    }

    public void addVariable(Variable v) {
        variables.add(v);
    }
}
