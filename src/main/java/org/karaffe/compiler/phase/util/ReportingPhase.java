package org.karaffe.compiler.phase.util;

import org.karaffe.compiler.phase.Phase;
import org.karaffe.compiler.util.CompilerContext;
import org.karaffe.compiler.util.report.Report;

public class ReportingPhase implements Phase {
  @Override
  public String getName() {
    return "reporting";
  }

  @Override
  public void execute(CompilerContext context) {
    for (Report report : context.getReports()) {
      if (report.hasPosition()) {
        System.out.println(report.getHeader() + " at " + report.getPosition());
      } else {
        System.out.println(report.getHeader());
      }
      if (report.hasBody()) {
        System.out.println(report.getBody());
      }
    }
  }
}
