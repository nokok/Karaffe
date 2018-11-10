package org.karaffe.compiler;

public class KaraffeCompiler {
    private final CompilerContext context;

    public KaraffeCompiler() {
        this(new CompilerContext());
    }

    public KaraffeCompiler(CompilerContext context) {
        this.context = context;
    }

    public void run() {
        if (this.context.getRawArgs().length == 0) {
            this.context.addOutputs("Usage:");
            this.context.addOutputs("  krfc <options> <sources>");
        }
    }

    public String out() {
        return context.getOutputText();
    }
}
