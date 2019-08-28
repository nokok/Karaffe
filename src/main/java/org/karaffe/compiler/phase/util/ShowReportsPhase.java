package org.karaffe.compiler.phase.util;

import org.karaffe.compiler.phase.Phase;
import org.karaffe.compiler.util.CompilerContext;
import org.karaffe.compiler.util.report.Report;
import org.karaffe.compiler.util.report.ReportType;

public class ShowReportsPhase implements Phase {
  @Override
  public String getName() {
    return "show-reports";
  }

  @Override
  public void execute(CompilerContext context) {
    for (Report report : context.getReports()) {
      String h;
      if (report.getReportType() == ReportType.ERROR) {
        h = "ERROR | ";
      } else if (report.getReportType() == ReportType.WARN) {
        h = "WARN  | ";
      } else {
        h = "";
      }
      String title;
      if (report.hasPosition()) {
        title = report.getHeader() + " at " + report.getPosition().orElseThrow();
      } else {
        title = report.getHeader();
      }
      context.writeLine(h + title);
      report.getBody().ifPresent(context::writeLine);
    }
  }
}
