package org.karaffe.compiler.util.args;

import java.util.Objects;

public class InvalidOptions extends Options {
  private String invalidOption;

  InvalidOptions(String invalidOption) {
    this.invalidOption = Objects.requireNonNull(invalidOption);
  }

  @Override
  public boolean isInvalid() {
    return true;
  }

  @Override
  public boolean hasFlag(Flag flag) {
    return false;
  }

  @Override
  public String toString() {
    return "InvalidOptions{" + invalidOption + "}";
  }
}
