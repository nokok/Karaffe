package org.karaffe.compiler.util.report;

import java.util.ResourceBundle;

public enum ReportCode {
  ERR_SYNTAX("frontend.syntax_error"),
  ERR_NAME_VALIDATION_FAILED("frontend.name_validation_failed"),
  ERR_CLASS_NOT_FOUND("frontend.not_found.class"),
  ERR_INVALID_INHERITANCE_UNEXPECTED_INTERFACE("frontend.invalid_inheritance.unexpected_interface"),
  ERR_INVALID_INHERITANCE_FINAL_CLASS("frontend.invalid_inheritance.final_class"),
  ERR_INVALID_INHERITANCE_ARRAY_TYPE("frontend.invalid_inheritance.array_type"),

  WARN_NAME_VALIDATION("frontend.name_validation_warning"),

  INFO_COMPILER_VERSION("compiler_internal.version"),
  ;

  private static final ResourceBundle bundle = ResourceBundle.getBundle("Message");
  private final String errorKey;

  ReportCode(String errorKey) {
    this.errorKey = errorKey;
  }

  public String toTitleName() {
    return bundle.getString(errorKey);
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
