package org.karaffe.compiler.util;

import java.nio.file.Path;
import java.util.Objects;

public class BytecodeEntry {
  private final Path path;
  private final byte[] byteCode;

  public BytecodeEntry(Path path, byte[] byteCode) {
    this.path = Objects.requireNonNull(path);
    this.byteCode = Objects.requireNonNull(byteCode);
  }

  public byte[] getByteCode() {
    return byteCode;
  }

  public Path getPath() {
    return path;
  }
}
