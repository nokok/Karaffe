package org.karaffe.compiler.args;

import java.util.Optional;

public enum Flag {
  DRY_RUN(null, "--dry-run"),
  VERSION(null, "--version"),
  STDIN("-", null),
  HELP("-h", "--help"),
  ;

  private final String shortName;
  private final String fullName;

  Flag(String shortName, String fullName) {
    this.shortName = shortName;
    this.fullName = fullName;
  }

  public Optional<String> getShortName() {
    return Optional.ofNullable(this.shortName);
  }

  public Optional<String> getFullName() {
    return Optional.ofNullable(this.fullName);
  }
}
