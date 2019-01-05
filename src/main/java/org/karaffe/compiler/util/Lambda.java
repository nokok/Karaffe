package org.karaffe.compiler.util;

import java.util.Optional;
import java.util.concurrent.Callable;

public class Lambda {
  public static <T> Optional<T> uncheck(Callable<T> c) {
    try {
      return Optional.ofNullable(c.call());
    } catch (Exception e) {
      return Optional.empty();
    }
  }

  public static void uncheck(Runnable r) {
    try {
      r.run();
    } catch (Exception e) {
      System.err.println("ERROR");
      e.printStackTrace();
    }
  }
}