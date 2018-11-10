package org.karaffe.compiler;

public class Main {
    public static void main(String[] args) {
        CompilerContext context = new CompilerContext();
        context.setRawArgs(args);
        KaraffeCompiler compiler = new KaraffeCompiler(context);
        compiler.run();
        System.out.println(context.getOutputText());
    }
}
