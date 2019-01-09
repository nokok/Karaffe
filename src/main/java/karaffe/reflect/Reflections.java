package karaffe.reflect;

public enum Reflections {
  INSTANCE,
  ;

  public static TypeMirror typeOf(Class<?> clazz) {
    return new TypeMirrorImpl(clazz);
  }
}
