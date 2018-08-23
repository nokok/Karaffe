package org.karaffe.compiler.base.mir.functions;

import org.karaffe.compiler.base.mir.tacs.Tac;
import org.karaffe.compiler.base.mir.types.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Function {
    private String functionName;
    private List<Argument> arguments;
    private Type returnType;
    private List<Tac> instructions;

    public Function(String functionName, List<Argument> arguments, Type returnType) {
        this.functionName = Objects.requireNonNull(functionName);
        this.arguments = Objects.requireNonNull(arguments);
        this.returnType = Objects.requireNonNull(returnType);
        this.instructions = new ArrayList<>();
    }

    public String getName() {
        return functionName;
    }


}
