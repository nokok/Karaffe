package org.karaffe.compiler.tree.walker;

public class TreeWalkCancellationException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public TreeWalkCancellationException() {
  }

  public TreeWalkCancellationException(String message) {
    super(message);
  }

  public TreeWalkCancellationException(String message, Throwable cause) {
    super(message, cause);
  }

  public TreeWalkCancellationException(Throwable cause) {
    super(cause);
  }

  public TreeWalkCancellationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
