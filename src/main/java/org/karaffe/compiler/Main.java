package org.karaffe.compiler;

import karaffe.core.Console;
import org.karaffe.compiler.util.args.Flag;
import org.karaffe.compiler.util.report.Report;
import org.karaffe.compiler.util.report.ReportCode;
import org.karaffe.compiler.tree.formatter.SimpleTreeFormatter;
import org.karaffe.compiler.util.CompilerContext;
import org.karaffe.compiler.util.KaraffeSource;

import java.util.ArrayList;
import java.util.List;
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
      System.out.println(formatter.format(context.getUntypedTree()));
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
        List<String> lines = new ArrayList<>();
        while (scanner.hasNextLine()) {
          lines.add(scanner.nextLine());
        }
        context.add(KaraffeSource.fromString(String.join("\n", lines), "<stdin>"));
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
