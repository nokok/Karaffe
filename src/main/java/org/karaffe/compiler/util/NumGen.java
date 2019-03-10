package org.karaffe.compiler.util;

public final class NumGen {
  private static long value = 0;

  public static long next() {
    return value++;
  }
}
