package org.karaffe.compiler;

import org.karaffe.compiler.backend.Backend;
import org.karaffe.compiler.frontend.Frontend;
import org.karaffe.compiler.tree.formatter.FormatType;
import org.karaffe.compiler.tree.formatter.InternalStateFormatter;
import org.karaffe.compiler.util.CompilerContext;
import org.karaffe.compiler.util.args.Flag;
import org.karaffe.compiler.util.args.ParameterName;
import org.karaffe.compiler.util.report.Report;
import org.karaffe.compiler.util.report.ReportCode;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class KaraffeCompiler implements Runnable {
  private final CompilerContext context;

  public KaraffeCompiler(CompilerContext context) {
    this.context = context;
  }

  @Override
  public void run() {
    Frontend frontend = Frontend.getFrontend(context);
    frontend.execute();

    if (context.hasError()) {
      return;
    }

    context.getParameter(ParameterName.EMIT).map(String::toLowerCase).ifPresent(param -> {
      FormatType type;
      if (param.equals("ast")) {
        type = FormatType.SIMPLE;
      } else if (param.equals("source")) {
        type = FormatType.SOURCE;
      } else {
        this.context.add(Report.newReport(ReportCode.ERR_UNRECOGNIZED_ARGUMENT).withVariable(param).build());
        return;
      }
      InternalStateFormatter formatter = InternalStateFormatter.fromType(type);
      this.context.add(Report.newReport(ReportCode.INFO_AST).withBody(formatter.format(this.context)).build());
    });

    Backend backend = Backend.getBackend(context);
    backend.execute();

    if (context.hasError()) {
      return;
    }

    if (this.context.hasFlag(Flag.DRY_RUN)) {
      return;
    }
    for (Map.Entry<Path, byte[]> entry : context.getOutputFiles().entrySet()) {
      try {
        Files.write(entry.getKey(), entry.getValue());
      } catch (IOException e) {
        throw new UncheckedIOException(e);
      }
    }
  }
}
