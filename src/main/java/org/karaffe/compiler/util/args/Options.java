package org.karaffe.compiler.util.args;

import java.util.ArrayList;
import java.util.List;

public class Options {

  public final boolean help;
  public final boolean version;
  public final boolean debugInfo;

  public Options() {
    this.help = false;
    this.version = false;
    this.debugInfo = false;
  }

  public Options(boolean help, boolean version, boolean debugInfo) {
    this.help = help;
    this.version = version;
    this.debugInfo = debugInfo;
  }

  @Override
  public String toString() {
    List<String> options = new ArrayList<>();
    if (help) {
      options.add("--help");
    }
    if (version) {
      options.add("--version");
    }
    if (debugInfo) {
      options.add("-g");
    }
    String v = "Options{";
    v += String.join(",", options);
    return v + "}";
  }
}