package org.karaffe.compiler.util.args;

import org.karaffe.compiler.util.KaraffeSource;
import org.karaffe.compiler.util.report.Report;
import org.karaffe.compiler.util.report.ReportCode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class ArgsParser {

  private final List<Report> reports = new ArrayList<>();

  private final Map<String, Flag> supportedFlags = new HashMap<>();
  private final Map<String, ParameterName> supportedParameters = new HashMap<>();

  public ArgsParser() {
    for (Flag flag : Flag.values()) {
      flag.getShortName().ifPresent(n -> supportedFlags.put(n, flag));
      flag.getFullName().ifPresent(n -> supportedFlags.put(n, flag));
    }
    for (ParameterName parameter : ParameterName.values()) {
      parameter.getShortName().ifPresent(n -> supportedParameters.put(n, parameter));
      parameter.getFullName().ifPresent(n -> supportedParameters.put(n, parameter));
    }
  }

  public Optional<Options> parse(String[] options) {
    Objects.requireNonNull(options);
    if (options.length == 0) {
      return Optional.of(new Options());
    }
    Set<Flag> recognizedFlags = new HashSet<>();
    Set<ParameterName> recognizedParameters = new HashSet<>();
    Map<ParameterName, String> recognizedParameterValues = new HashMap<>();
    Set<KaraffeSource> sourceSet = new HashSet<>();
    for (int i = 0; i < options.length; i++) {
      String c = options[i];
      if (c.startsWith("-")) {
        boolean containsKeyAtFlag = supportedFlags.containsKey(c);
        boolean containsKeyAtParameter = supportedParameters.containsKey(c);
        if (containsKeyAtFlag) {
          if (!recognizedFlags.add(supportedFlags.get(c))) {
            this.reports.add(Report.newReport(ReportCode.ERR_DUPLICATE_FLAG).withVariable(c).build());
          }
        } else if (containsKeyAtParameter) {
          ParameterName parameterName = supportedParameters.get(c);
          if (!recognizedParameters.add(parameterName)) {
            this.reports.add(Report.newReport(ReportCode.ERR_DUPLICATE_PARAMETER).withVariable(c).build());
            continue;
          }
          if (options.length <= i + 1) {
            this.reports.add(Report.newReport(ReportCode.ERR_OPTION_REQUIRES_ARGUMENT).withVariable(c).build());
            continue;
          }
          String argument = options[++i];
          if (!parameterName.isSupportedArg(argument)) {
            this.reports.add(Report.newReport(ReportCode.ERR_UNRECOGNIZED_ARGUMENT).withVariable(argument).build());
          }
          recognizedParameterValues.put(parameterName, argument);
        } else {
          this.reports.add(Report.newReport(ReportCode.ERR_UNRECOGNIZED_ARGUMENT).withVariable(c).build());
        }
      } else {
        Path filePath = Paths.get(c);
        if (Files.exists(filePath)) {
          try {
            sourceSet.add(KaraffeSource.fromPath(filePath));
          } catch (IOException e) {
            this.reports.add(Report.newReport(ReportCode.ERR_IO_EXCEPTION).withVariable(e.getLocalizedMessage()).build());
          }
        } else {
          this.reports.add(Report.newReport(ReportCode.ERR_FILE_NOT_FOUND).withVariable(c).build());
        }
      }
    }
    if (this.reports.isEmpty()) {
      return Optional.of(new Options(recognizedFlags, recognizedParameterValues, sourceSet));
    } else {
      return Optional.empty();
    }

  }

  public List<Report> getReports() {
    return reports;
  }
}
