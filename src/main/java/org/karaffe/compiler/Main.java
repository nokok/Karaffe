package org.karaffe.compiler;

import karaffe.core.Console;

public class Main {
    public static void main(String[] args) {
        CompilerContext context = new CompilerContext();
        context.parseRawArgs(args);
        KaraffeCompiler compiler = new KaraffeCompiler(context);
        compiler.run();
        Console.print(context.hasOutputText() ? context.getOutputText() + "\n" : "");
    }
}
