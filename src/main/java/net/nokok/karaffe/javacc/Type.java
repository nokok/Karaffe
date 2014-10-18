package net.nokok.karaffe.javacc;

import java.util.ArrayList;
import java.util.List;

public class Type {

    private final Name typeName;
    private final List<Function> functions = new ArrayList<>();
    private final List<Variable> fields = new ArrayList<>();
    private final List<Function> globalFunctions = new ArrayList<>();
    private final List<Variable> globalFields = new ArrayList<>();

    public Type(Name name) {
        this.typeName = name;
    }

    public String getName() {
        return typeName.toString();
    }

    public void resetScope() {
        functions.clear();
        fields.clear();
    }

}
