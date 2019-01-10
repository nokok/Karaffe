package org.karaffe.compiler.util.args;

import org.karaffe.compiler.util.KaraffeSource;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public class Options {

  private Set<Flag> flags;
  private Map<ParameterName, String> parameterValues;
  private Set<KaraffeSource> sources;

  public Options() {
    this(new HashSet<>(0), new HashMap<>(0), new HashSet<>(0));
  }

  public Options(Set<Flag> flags, Map<ParameterName, String> parameterValues, Set<KaraffeSource> sources) {
    this.flags = Objects.requireNonNull(flags);
    this.parameterValues = Objects.requireNonNull(parameterValues);
    this.sources = Objects.requireNonNull(sources);
  }

  public boolean isEmpty() {
    return this.flags.isEmpty();
  }

  public boolean hasFlag(Flag flag) {
    return this.flags.contains(flag);
  }

  public boolean hasParameter(ParameterName parameterName) {
    return this.parameterValues.containsKey(parameterName);
  }

  public Optional<String> getParameter(ParameterName parameterName) {
    if (this.hasParameter(parameterName)) {
      return Optional.of(this.parameterValues.get(parameterName));
    } else {
      return Optional.empty();
    }
  }

  public Stream<KaraffeSource> sourceStream() {
    return this.sources.stream();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Options options = (Options) o;
    return flags.equals(options.flags) &&
           parameterValues.equals(options.parameterValues) &&
           sources.equals(options.sources);
  }

  @Override
  public int hashCode() {
    return Objects.hash(flags, parameterValues, sources);
  }

  @Override
  public String toString() {
    return "Options{" +
           "flags=" + flags +
           ", parameterValues=" + parameterValues +
           ", sources=" + sources +
           '}';
  }
}
