package karaffe.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class TypeMirrorImpl implements TypeMirror {

  private Class<?> clazz;

  @Override
  public boolean isInstance(Object obj) {
    return clazz.isInstance(obj);
  }

  @Override
  public boolean isAssignableFrom(Class<?> cls) {
    return clazz.isAssignableFrom(cls);
  }

  @Override
  public boolean isInterface() {
    return clazz.isInterface();
  }

  @Override
  public boolean isArray() {
    return clazz.isArray();
  }

  @Override
  public boolean isPrimitive() {
    return clazz.isPrimitive();
  }

  @Override
  public boolean isAnnotation() {
    return clazz.isAnnotation();
  }

  @Override
  public boolean isSynthetic() {
    return clazz.isSynthetic();
  }

  @Override
  public boolean isAnonymousClass() {
    return clazz.isAnonymousClass();
  }

  @Override
  public boolean isLocalClass() {
    return clazz.isLocalClass();
  }

  @Override
  public boolean isMemberClass() {
    return clazz.isMemberClass();
  }

  @Override
  public boolean isEnum() {
    return clazz.isEnum();
  }

  @Override
  public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {
    return clazz.isAnnotationPresent(annotationClass);
  }

  @Override
  public boolean isNestmateOf(Class<?> c) {
    try {
      Method method = this.clazz.getMethod("isNestmateOf", Class.class);
      Object result = method.invoke(this.clazz, c);
      if (result instanceof Boolean) {
        return (Boolean) result;
      } else {
        return false;
      }
    } catch (ReflectiveOperationException e) {
      return false;
    }
  }

  public TypeMirrorImpl(Class<?> clazz) {
    this.clazz = Objects.requireNonNull(clazz);
  }

  @Override
  public Class<?> asClass() {
    return this.clazz;
  }

  @Override
  public Set<Member> decls() {
    clazz.getFields();
    clazz.getConstructors();
    clazz.getMethods();
    clazz.getClasses();
    return new HashSet<>();
  }
}
