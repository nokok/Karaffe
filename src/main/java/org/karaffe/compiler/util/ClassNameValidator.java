package org.karaffe.compiler.util;

import static org.karaffe.compiler.util.NameValidationResult.ERR_EMPTY_NAME;
import static org.karaffe.compiler.util.NameValidationResult.ERR_INVALID_JAVA_IDENTIFIER;
import static org.karaffe.compiler.util.NameValidationResult.ERR_LAMBDA_KEYWORD;
import static org.karaffe.compiler.util.NameValidationResult.ERR_NULL;
import static org.karaffe.compiler.util.NameValidationResult.OK;
import static org.karaffe.compiler.util.NameValidationResult.WARN_CAMEL_CASE;

public class ClassNameValidator {

  public NameValidationResult validate(String name) {
    if (name == null) {
      return ERR_NULL;
    }
    if (name.isEmpty()) {
      return ERR_EMPTY_NAME;
    }
    if (!this.isJavaIdentifier(name)) {
      return ERR_INVALID_JAVA_IDENTIFIER;
    }
    if (Character.isLowerCase(name.charAt(0))) {
      return WARN_CAMEL_CASE;
    }
    if (name.startsWith("_")) {
      return ERR_LAMBDA_KEYWORD;
    }
    return OK;
  }

  private boolean isJavaIdentifier(String id) {
    boolean identifierStart = Character.isJavaIdentifierStart(id.charAt(0));
    if (!identifierStart) return false;
    for (char c : id.toCharArray()) {
      if (!Character.isJavaIdentifierPart(c)) {
        return false;
      }
    }
    return true;
  }
}
