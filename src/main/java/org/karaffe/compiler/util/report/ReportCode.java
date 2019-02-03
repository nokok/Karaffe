package org.karaffe.compiler.util.report;

import java.util.ResourceBundle;

public enum ReportCode {
  ERR_UNKNOWN("unknown"),
  ERR_SYNTAX("syntax"),
  ERR_DUPLICATE_CLASS_DECLARATION("duplicate_class_declaration"),
  ERR_DUPLICATE_DECLARATION("duplicate_declaration"),
  ERR_UNRECOGNIZED_ARGUMENT("unrecognized_argument"),
  ERR_FILE_NOT_FOUND("file_not_found"),
  ERR_IO_EXCEPTION("io_exception"),
  ERR_OPTION_REQUIRES_ARGUMENT("option_requires_argument"),
  ERR_DUPLICATE_PARAMETER("duplicate_parameter"),
  ERR_DUPLICATE_FLAG("duplicate_flag"),
  ERR_NAME_VALIDATION_FAILED("name_validation_failed"),
  ERR_CLASS_NOT_FOUND("class_not_found"),
  ERR_INVALID_INHERITANCE_UNEXPECTED_INTERFACE("invalid_inheritance.unexpected_interface"),
  ERR_INVALID_INHERITANCE_FINAL_CLASS("invalid_inheritance.final_class"),
  ERR_INVALID_INHERITANCE_ARRAY_TYPE("invalid_inheritance.array_type"),
  ERR_SYMBOL_NOT_FOUND_CLASS("symbol_not_found.class"),
  ERR_SYMBOL_NOT_FOUND_METHOD("symbol_not_found.method"),
  ERR_SYMBOL_NOT_FOUND("symbol_not_found.other"),
  ERR_SHADOWING_DISABLED("disabled_shadowing"),

  WARN_CLASSNAME_IS_NOT_PASCALCASE("classname_is_not_pascalcase"),
  WARN_NAME_VALIDATION("name_validation_warning"),

  INFO_COMPILER_VERSION("compiler_version"),
  INFO_USAGE("usage"),
  INFO_AST("emit_ast"),
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
