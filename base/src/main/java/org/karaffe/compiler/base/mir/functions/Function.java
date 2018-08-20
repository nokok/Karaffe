package org.karaffe.compiler.base.mir.functions;

import org.karaffe.compiler.base.mir.tacs.Tac;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Function {
    private String functionName;
    private List<Tac> instructions;

    public Function(String functionName) {
        this.functionName = Objects.requireNonNull(functionName);
        this.instructions = new ArrayList<>();
    }

    public String getName() {
        return functionName;
    }


}
