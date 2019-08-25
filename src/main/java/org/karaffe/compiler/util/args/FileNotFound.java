package org.karaffe.compiler.util.args;

public class FileNotFound extends Options {
  private final String fileName;

  FileNotFound(String fileName) {
    this.fileName = fileName;
  }

  @Override
  public boolean isInvalid() {
    return true;
  }

  @Override
  public boolean isValidFiles() {
    return false;
  }

  public String getFileName() {
    return fileName;
  }

  @Override
  public boolean hasFlag(Flag flag) {
    return false;
  }

  @Override
  public String toString() {
    return "FileNotFound{" + this.fileName + "}";
  }
}
