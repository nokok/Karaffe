package org.karaffe.compiler;

import karaffe.core.Console;
import org.karaffe.compiler.args.Flag;
import org.karaffe.compiler.report.Report;
import org.karaffe.compiler.tree.formatter.SimpleTreeFormatter;
import org.karaffe.compiler.util.CompilerContext;

public class Main {

  private CompilerContext context = new CompilerContext();

  public static void main(String[] args) {
    Main main = new Main();
    main.run(args);
  }

  public void run(String[] args) {
    Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
      System.out.println("===ERROR===");
      if (context == null) {
        return;
      }
      SimpleTreeFormatter formatter = new SimpleTreeFormatter();
      System.out.println(formatter.format(context.getCurrentAST()));
      throwable.printStackTrace();
    });
    context.parseRawArgs(args);
    if (context.hasFlag(Flag.VERSION)) {
      context.add(Report.newInfoReport("Karaffe compiler version: 0.1.0").build());
    } else if (context.requireShowUsage()) {
      context.add(Report
                  .newInfoReport("Usage:")
                  .withBody("krfc <options> <sources>")
                  .build());
    } else {
      KaraffeCompiler compiler = new KaraffeCompiler(context);
      compiler.run();
    }
    String outputText = context.getOutputText();
    if (!outputText.isEmpty()) {
      Console.println(outputText);
    }
  }

  public CompilerContext getContext() {
    return context;
  }
}
