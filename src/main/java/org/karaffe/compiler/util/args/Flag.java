package org.karaffe.compiler.util.args;

import java.util.Arrays;
import java.util.List;

public enum Flag {
  VERSION("--version"),
  HELP("--help", "-h"),
  DEBUG("-g");

  private final List<String> args;

  Flag(String... args) {
    this.args = Arrays.asList(args);
  }

  public boolean is(String arg) {
    return args.contains(arg);
  }

  @Override
  public String toString() {
    return this.args.get(0);
  }
}
