package org.karaffe.compiler.report;

import java.util.ResourceBundle;

public enum ReportCode {
  ERR_UNKNOWN("unknown"),
  ERR_SYNTAX("syntax"),
  ERR_DUPLICATE_CLASS_DECLARATION("duplicate_class_declaration"),
  ERR_UNRECOGNIZED_ARGUMENT("unrecognized_argument"),
  ERR_FILE_NOT_FOUND("file_not_found"),
  ERR_IO_EXCEPTION("io_exception"),
  ERR_OPTION_REQUIRES_ARGUMENT("option_requires_argument"),
  ERR_DUPLICATE_PARAMETER("duplicate_parameter"),
  ERR_DUPLICATE_FLAG("duplicate_flag"),
  ERR_NAME_VALIDATION_FAILED("name_validation_failed"),

  WARN_CLASSNAME_IS_NOT_PASCALCASE("classname_is_not_pascalcase"),
  WARN_NAME_VALIDATION("name_validation_warning"),

  INFO_COMPILER_VERSION("compiler_version"),
  INFO_USAGE("usage"),
  INFO_AST("emit_ast"),
  ;

  private final String errorKey;
  private static final ResourceBundle bundle = ResourceBundle.getBundle("Message");

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
