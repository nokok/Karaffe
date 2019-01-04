package org.karaffe.compiler.util;

public enum NameValidationResult {
  ERR_NULL,
  ERR_EMPTY_NAME,
  ERR_INVALID_JAVA_IDENTIFIER,
  ERR_LAMBDA_KEYWORD,
  WARN_CAMEL_CASE,
  OK
}
