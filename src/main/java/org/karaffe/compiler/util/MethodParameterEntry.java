package org.karaffe.compiler.util;

import java.util.Objects;

public class MethodParameterEntry {
  private String name;
  private Class<?> type;

  public MethodParameterEntry(String name, Class<?> type) {
    this.name = Objects.requireNonNull(name);
    this.type = Objects.requireNonNull(type);
  }

  @Override
  public String toString() {
    return this.name + ":" + type.getCanonicalName();
  }
}
