package org.karaffe.compiler.platform;

public enum JDKVersion {
  JDK1_8(52, 0, "JDK 8"),
  JDK9(53, 0, "JDK 9"),
  JDK10(54, 0, "JDK 10"),
  JDK11(55, 0, "JDK 11"),
  ;
  private final int major;
  private final int minor;
  private final String version;

  JDKVersion(int major, int minor, String version) {
    this.major = major;
    this.minor = minor;
    this.version = version;
  }

  @Override
  public String toString() {
    return version;
  }
}
