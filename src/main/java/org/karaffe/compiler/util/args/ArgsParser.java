package org.karaffe.compiler.util.args;

import org.karaffe.compiler.util.StartupEnv;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ArgsParser {

  public Options parse(StartupEnv env) {
    return this.parse(env.getCommandLineArgs());
  }

  public Options parse(String[] args) {
    Set<Flag> flags = new HashSet<>();
    for (String arg : args) {
      Optional<Flag> optFlag = Flag.of(arg);
      if (optFlag.isEmpty()) {
        return new InvalidOptions(arg);
      }
      optFlag.ifPresent(flags::add);
    }
    return new Options(flags);
  }
}
