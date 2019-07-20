package org.karaffe.compiler.util.args;

import org.karaffe.compiler.util.StartupEnv;

public class ArgsParser {

  public Options parse(StartupEnv env) {
    return this.parse(env.getCommandLineArgs());
  }

  public Options parse(String[] args) {
    boolean help = false;
    boolean version = false;
    boolean debugInfo = false;
    for (String arg : args) {
      if (arg.equals("--help")) {
        help = true;
        continue;
      }
      if (arg.equals("--version")) {
        version = true;
        continue;
      }
      if (arg.equals("-g")) {
        debugInfo = true;
      }
    }
    return new Options(help, version, debugInfo);
  }
}
