package org.karaffe.compiler.util;

public class DefaultExceptionHandler implements Thread.UncaughtExceptionHandler {

  @Override
  public void uncaughtException(Thread t, Throwable e) {
    e.printStackTrace();
  }
}
