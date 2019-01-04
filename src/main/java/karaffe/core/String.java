package karaffe.core;

import java.util.Objects;

public class String {

  private final java.lang.String value;

  public String(java.lang.String value) {
    this.value = Objects.requireNonNull(value);
  }

  public String plus(String other) {
    return new karaffe.core.String(this.value + other.value);
  }

  @Override
  public java.lang.String toString() {
    return this.value;
  }
}
