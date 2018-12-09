package org.karaffe.compiler;

import karaffe.core.Console;
import org.karaffe.compiler.report.Report;
import org.karaffe.compiler.util.CompilerContext;

public class Main {

    private static CompilerContext context = new CompilerContext();

    public static void main(String[] args) {
        context.parseRawArgs(args);
        if (context.requireShowUsage()) {
            context.add(Report.newInfoReport("Usage:").withBody("krfc <options> <sources>").build());
        } else {
            KaraffeCompiler compiler = new KaraffeCompiler(context);
            compiler.run();
        }
        String outputText = context.getOutputText();
        if (!outputText.isEmpty()) {
            Console.println(outputText);
        }
    }

    public static CompilerContext getContext() {
        return context;
    }
}
