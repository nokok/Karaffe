package org.karaffe.compiler.util.args;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public enum Flag {
  VERSION("--version"),
  HELP("--help", "-h"),
  DEBUG("-g"),
  VERBOSE("-v");

  private static final ResourceBundle bundle = ResourceBundle.getBundle("Message");
  private final List<String> args;

  Flag(String... args) {
    this.args = Arrays.asList(args);
  }

  public boolean is(String arg) {
    return args.contains(arg);
  }

  public static Optional<Flag> of(String value) {
    for (Flag flag : Flag.values()) {
      if (flag.is(value)) {
        return Optional.of(flag);
      }
    }
    return Optional.empty();
  }

  public String getDescription() {
    return bundle.getString("usage." + this.name().toLowerCase());
  }

  @Override
  public String toString() {
    return this.args.get(0);
  }

  public String toFullString() {
    return String.join(",", this.args);
  }
}
