package org.karaffe.compiler;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CompilerContext {
    private String[] rawArgs;
    private List<String> outputs = new ArrayList<>();

    public String[] getRawArgs() {
        return rawArgs;
    }

    public void setRawArgs(String[] rawArgs) {
        this.rawArgs = Objects.requireNonNull(rawArgs);
    }

    public void addOutputs(String line) {
        this.outputs.add(Objects.requireNonNull(line));
    }

    public String getOutputText() {
        return String.join("\n", outputs);
    }
}
