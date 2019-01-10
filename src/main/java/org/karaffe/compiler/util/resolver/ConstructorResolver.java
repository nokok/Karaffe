package org.karaffe.compiler.util.resolver;

import java.lang.reflect.Constructor;
import java.util.Objects;
import java.util.Optional;

public class ConstructorResolver {
  private final Class<?> clazz;

  public ConstructorResolver(Class<?> clazz) {
    this.clazz = Objects.requireNonNull(clazz);
  }

  public Optional<Constructor<?>> getConstructor(Class<?>... parameters) {
    try {
      Constructor<?> constructor = this.clazz.getConstructor(parameters);
      return Optional.of(constructor);
    } catch (NoSuchMethodException e) {
      return Optional.empty();
    }
  }
}
