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

    }

    public String out() {
        return "";
    }
}
