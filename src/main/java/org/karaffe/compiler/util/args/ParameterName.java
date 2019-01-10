package org.karaffe.compiler.util.args;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ParameterName {
  EMIT(null, "--emit", "ast"),
  ;

  private final String shortName;
  private final String fullName;
  private final Pattern supportedArgPattern;

  ParameterName(String shortName, String fullName, String supportedArgPattern) {
    this.shortName = shortName;
    this.fullName = fullName;
    this.supportedArgPattern = Pattern.compile("^" + supportedArgPattern + "$");
  }

  public Optional<String> getShortName() {
    return Optional.ofNullable(this.shortName);
  }

  public Optional<String> getFullName() {
    return Optional.ofNullable(this.fullName);
  }

  public boolean isSupportedArg(String rawArg) {
    Matcher matcher = this.supportedArgPattern.matcher(rawArg);
    return matcher.find();
  }
}
