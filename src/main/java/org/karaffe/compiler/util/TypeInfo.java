package org.karaffe.compiler.util;

import java.util.Objects;

public class TypeInfo {
  private final Class<?> clazz;

  public TypeInfo(Class<?> clazz) {
    this.clazz = clazz;
  }

  public static TypeInfo error() {
    return new TypeInfo(null);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TypeInfo typeInfo = (TypeInfo) o;
    return Objects.equals(clazz, typeInfo.clazz);
  }

  @Override
  public int hashCode() {
    return Objects.hash(clazz);
  }
}
