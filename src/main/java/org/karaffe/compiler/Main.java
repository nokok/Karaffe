package org.karaffe.compiler;

import karaffe.core.Console;

public class Main {

    private static CompilerContext context = new CompilerContext();

    public static void main(String[] args) {
        context.parseRawArgs(args);
        if (context.requireShowUsage()) {
            context.addOutputText("Usage:");
            context.addOutputText("  krfc <options> <sources>");
            return;
        }
        KaraffeCompiler compiler = new KaraffeCompiler(context);
        compiler.run();
        Console.print(context.hasOutputText() ? context.getOutputText() + "\n" : "");
    }

    public static CompilerContext getContext() {
        return Main.context;
    }
}
