package org.karaffe.compiler;

import java.util.Objects;

public class CompilerContext {
    private String[] rawArgs;

    public String[] getRawArgs() {
        return rawArgs;
    }

    public void setRawArgs(String[] rawArgs) {
        this.rawArgs = Objects.requireNonNull(rawArgs);
    }
}
