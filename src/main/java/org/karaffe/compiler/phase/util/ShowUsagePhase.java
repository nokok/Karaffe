package org.karaffe.compiler.phase.util;

import org.karaffe.compiler.phase.Phase;
import org.karaffe.compiler.util.CompilerContext;
import org.karaffe.compiler.util.args.Flag;
import org.karaffe.compiler.util.args.ParameterName;
import org.karaffe.compiler.util.report.Report;
import org.karaffe.compiler.util.report.ReportCode;

import java.util.Objects;

public class ShowUsagePhase implements Phase {

  private final Flag[] supportedFlags;
  private final ParameterName[] supportedParameterNames;

  public ShowUsagePhase() {
    this.supportedFlags = new Flag[0];
    this.supportedParameterNames = new ParameterName[0];
  }

  public ShowUsagePhase(Flag[] supportedFlags, ParameterName[] supportedParameterNames) {
    this.supportedFlags = Objects.requireNonNull(supportedFlags);
    this.supportedParameterNames = Objects.requireNonNull(supportedParameterNames);
  }

  @Override
  public String getName() {
    return "show-usage";
  }

  @Override
  public void execute(CompilerContext context) {
    StringBuilder stringBuilder = new StringBuilder();

    int maxWidthLeft = 0;
    for (Flag flag : supportedFlags) {
      maxWidthLeft = Math.max(flag.toFullString().length(), maxWidthLeft);
    }

    maxWidthLeft += 2;

    String formatText = "%-" + maxWidthLeft + "s";

    for (Flag flag : supportedFlags) {
      stringBuilder.append(String.format(formatText, flag.toFullString())).append(flag.getDescription()).append(System.lineSeparator());
    }
    context.add(Report.newReport(ReportCode.INFO_COMPILER_INTERNAL_USAGE).withBody(stringBuilder.toString()).build());
  }
}
