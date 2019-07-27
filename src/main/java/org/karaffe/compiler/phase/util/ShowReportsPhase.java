package org.karaffe.compiler.phase.util;

import org.karaffe.compiler.phase.Phase;
import org.karaffe.compiler.util.CompilerContext;
import org.karaffe.compiler.util.report.Report;

public class ShowReportsPhase implements Phase {
  @Override
  public String getName() {
    return "show-reports";
  }

  @Override
  public void execute(CompilerContext context) {
    for (Report report : context.getReports()) {
      if (report.hasPosition()) {
        context.writeLine(report.getHeader() + " at " + report.getPosition());
      } else {
        context.writeLine(report.getHeader());
      }
      report.getBody().ifPresent(context::writeLine);
    }
  }
}
