package org.karaffe.compiler.util;

public class Lambda {
  public static void uncheck(Runnable r) {
    try {
      r.run();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}