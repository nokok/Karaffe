package org.karaffe.compiler;

public class Main {
    public static void main(String[] args) {
        CompilerContext context = new CompilerContext();
        context.parseRawArgs(args);
        KaraffeCompiler compiler = new KaraffeCompiler(context);
        compiler.run();
        System.out.print(context.hasNoOutputText() ? "" : context.getOutputText() + "\n");
    }
}
