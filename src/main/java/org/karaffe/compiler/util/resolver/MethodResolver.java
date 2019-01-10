package org.karaffe.compiler.util.resolver;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Optional;

public class MethodResolver {
  private final Class<?> owner;

  public MethodResolver(Class<?> owner) {
    this.owner = Objects.requireNonNull(owner);
  }

  public boolean isResolvable() {
    return !this.owner.isPrimitive();
  }

  public boolean hasMethod(String methodName) {
    for (Method method : owner.getMethods()) {
      if (method.getName().equals(methodName)) {
        return true;
      }
    }
    return false;
  }

  public Optional<Method> getMethod(String methodName, Class<?>... parameters) {
    try {
      return Optional.of(owner.getMethod(methodName, parameters));
    } catch (NoSuchMethodException e) {
      return Optional.empty();
    }
  }

  public Optional<Method> getCompatibleMethod(String methodName, Class<?>... parameters) {
    if (!this.hasMethod(methodName)) {
      return Optional.empty();
    }
    try {
      return Optional.of(owner.getMethod(methodName, parameters));
    } catch (NoSuchMethodException e) {
      for (Method method : owner.getMethods()) {
        if (method.getName().equals(methodName) && parameters.length == method.getParameterCount()) {
          Class<?>[] parameterTypes = method.getParameterTypes();
          for (int i = 0; i < method.getParameterCount(); i++) {
            Class<?> parameterType = parameterTypes[i];
            Class<?> parameter = parameters[i];
            if (!parameterType.isAssignableFrom(parameter)) {
              return Optional.empty();
            }
          }
          return Optional.of(method);
        }
      }
      return Optional.empty();
    }
  }
}
