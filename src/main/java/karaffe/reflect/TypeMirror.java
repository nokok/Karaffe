package karaffe.reflect;

import java.lang.annotation.Annotation;
import java.util.Set;

public interface TypeMirror extends Mirrors {
  Class<?> asClass();

  Set<Member> decls();

  boolean isInstance(Object obj);

  boolean isAssignableFrom(Class<?> clazz);

  boolean isInterface();

  boolean isArray();

  boolean isPrimitive();

  boolean isAnnotation();

  boolean isSynthetic();

  boolean isAnonymousClass();

  boolean isLocalClass();

  boolean isMemberClass();

  boolean isEnum();

  boolean isAnnotationPresent(Class<? extends Annotation> annotationClass);

  boolean isNestmateOf(Class<?> c);

}
