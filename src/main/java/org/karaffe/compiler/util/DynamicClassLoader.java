package org.karaffe.compiler.util;

public class DynamicClassLoader extends ClassLoader {
  public DynamicClassLoader(ClassLoader parent) {
    super(parent);
  }

  public Class<?> define(String className, byte[] byteCode) {
    return super.defineClass(className, byteCode, 0, byteCode.length);
  }
}
