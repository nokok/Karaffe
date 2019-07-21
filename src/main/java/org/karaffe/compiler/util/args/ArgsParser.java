package org.karaffe.compiler.util.args;

import org.karaffe.compiler.util.StartupEnv;

import java.util.HashSet;
import java.util.Set;

public class ArgsParser {

  public Options parse(StartupEnv env) {
    return this.parse(env.getCommandLineArgs());
  }

  public Options parse(String[] args) {
    Set<Flag> flags = new HashSet<>();
    for (Flag flag : Flag.values()) {
      for (String arg : args) {
        if (flag.is(arg)) {
          flags.add(flag);
        }
      }
    }
    return new Options(flags);
  }
}
