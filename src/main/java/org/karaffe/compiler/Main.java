package org.karaffe.compiler;

import karaffe.core.Console;
import org.karaffe.compiler.args.Flag;
import org.karaffe.compiler.report.Report;
import org.karaffe.compiler.report.ReportCode;
import org.karaffe.compiler.tree.formatter.SimpleTreeFormatter;
import org.karaffe.compiler.util.CompilerContext;
import org.karaffe.compiler.util.KaraffeSource;

import java.util.Scanner;

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
      context.add(Report.newReport(ReportCode.INFO_COMPILER_VERSION).withVariable("0.1.0").build());
    } else if (context.requireShowUsage()) {
      context.add(Report.newReport(ReportCode.INFO_USAGE) /*.withVariable(supportedOptions)*/.withBody("krfc <options> <sources>").build());
    } else {
      if (context.hasFlag(Flag.STDIN)) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder sourceCode = new StringBuilder();
        while (scanner.hasNextLine()) {
          sourceCode.append(scanner.nextLine()).append("\n");
        }
        context.add(KaraffeSource.fromString(sourceCode.toString(), "<stdin>"));
      }
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
