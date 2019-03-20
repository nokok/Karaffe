package org.karaffe.compiler.util;

public final class NumGen {
  private long value = 0;

  public long next() {
    return value++;
  }
}
