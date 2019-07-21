package org.karaffe.compiler.phase.util;

import org.karaffe.compiler.CompilerConstants;
import org.karaffe.compiler.phase.Phase;
import org.karaffe.compiler.util.CompilerContext;
import org.karaffe.compiler.util.report.Report;
import org.karaffe.compiler.util.report.ReportCode;

public class ShowVersionPhase implements Phase {
  @Override
  public String getName() {
    return "show-version";
  }

  @Override
  public void execute(CompilerContext context) {
    context.add(Report.newReport(ReportCode.INFO_COMPILER_INTERNAL_VERSION).withVariable(CompilerConstants.VERSION).build());
  }
}
