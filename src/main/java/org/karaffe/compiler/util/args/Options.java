package org.karaffe.compiler.util.args;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Options {

  private final Set<Flag> flags;

  public Options() {
    this.flags = new HashSet<>(0);
  }

  public Options(Set<Flag> flags) {
    this.flags = Objects.requireNonNull(flags);
  }

  @Override
  public String toString() {
    List<String> options = new ArrayList<>();
    for (Flag flag : flags) {
      options.add(flag.toString());
    }
    String v = "Options{";
    v += String.join(",", options);
    return v + "}";
  }

  public boolean hasFlag(Flag flag) {
    return this.flags.contains(flag);
  }
}