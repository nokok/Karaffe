package org.karaffe.compiler.base;

import org.karaffe.compiler.base.util.config.Options;

import java.util.ArrayList;
import java.util.List;

public enum CompilerContext {
    CONTEXT,;

    private String state;
    public Options cmdLineOptions = new Options();


    public void setState(Class<?> phaseClass) {
        this.state = phaseClass.getCanonicalName();
    }


    @Override
    public String toString() {
        List<String> lines = new ArrayList<>();
        return String.join("\n", lines);
    }
}
