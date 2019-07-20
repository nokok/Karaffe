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

    Backend backend = Backend.getBackend(context);
    backend.execute();

  }
}
