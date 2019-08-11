package org.karaffe.compiler.util.args;

import org.karaffe.compiler.util.KaraffeSource;
import org.karaffe.compiler.util.StartupEnv;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ArgsParser {

  public Options parse(StartupEnv env) {
    return this.parse(env.getCommandLineArgs());
  }

  public Options parse(String[] args) {
    Set<Flag> flags = new HashSet<>();
    Set<KaraffeSource> sources = new HashSet<>();
    for (String arg : args) {
      Optional<Flag> optFlag = Flag.of(arg);
      optFlag.ifPresent(flags::add);
      if (optFlag.isEmpty()) {
        if (arg.endsWith(".krf")) {
          try {
            sources.add(KaraffeSource.fromPath(Paths.get(arg)));
          } catch (IOException e) {
            return new InvalidOptions(arg);
          }
        } else {
          return new InvalidOptions(arg);
        }
      }
    }
    return new Options(flags, sources);
  }
}
