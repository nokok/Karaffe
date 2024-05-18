package org.karaffe.compiler.util.report;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ReportCode {
  ERR_FRONTEND_SYNTAX
    (true, false, "frontend.syntax_error"),
  ERR_IO_FILE_NOT_FOUND(
    false, false, "io.file_not_found"),
  INFO_COMPILER_INTERNAL_VERSION
    (false, false, "compiler_internal.version"),
  INFO_COMPILER_INTERNAL_USAGE
    (false, true, "compiler_internal.usage"),
  ;

  private static final Pattern variablePattern = Pattern.compile("\\{[0-9]+\\}");
  private static final ResourceBundle bundle = ResourceBundle.getBundle("Message", Locale.getDefault());
  private final String errorKey;
  private final boolean requireBody;
  private final boolean requirePosition;

  ReportCode(boolean requirePosition, boolean requireBody, String errorKey) {
    this.errorKey = errorKey;
    this.requireBody = requireBody;
    this.requirePosition = requirePosition;
  }

  public String toReportHeader() {
    return bundle.getString(errorKey);
  }

  public boolean isRequireVariable() {
    return toReportHeader().contains("{0}");
  }

  public int getVarCount() {
    Matcher matcher = variablePattern.matcher(this.toReportHeader());
    int count = 0;
    while (matcher.find()) {
      count++;
    }
    return count;
  }

  public boolean isRequireBody() {
    return requireBody;
  }

  public boolean isRequirePosition() {
    return requirePosition;
  }

  public ReportType toReportType() {
    if (this.name().startsWith("ERR")) {
      return ReportType.ERROR;
    } else if (this.name().startsWith("WARN")) {
      return ReportType.WARN;
    }
    return ReportType.INFO;
  }
}
