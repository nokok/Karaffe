package org.karaffe.compiler.util.args;

import org.karaffe.compiler.util.KaraffeSource;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Options {

  private final Set<Flag> flags;
  private final Set<KaraffeSource> sources;

  public Options() {
    this.flags = new HashSet<>(0);
    this.sources = new HashSet<>(0);
  }

  Options(Set<Flag> flags, Set<KaraffeSource> sources) {
    this.flags = Objects.requireNonNull(flags);
    this.sources = Objects.requireNonNull(sources);
  }

  public boolean isInvalid() {
    return false;
  }

  public boolean isEmpty() {
    return this.flags.isEmpty() && this.sources.isEmpty();
  }

  public Set<KaraffeSource> getSources() {
    return this.sources;
  }


  @Override
  public String toString() {
    List<String> options = new ArrayList<>();
    for (Flag flag : flags) {
      options.add(flag.toString());
    }
    for (KaraffeSource source : sources) {
      options.add(source.getSourceName());
    }
    String v = "Options{";
    v += String.join(",", options);
    return v + "}";
  }

  public boolean hasFlag(Flag flag) {
    return this.flags.contains(flag);
  }
}