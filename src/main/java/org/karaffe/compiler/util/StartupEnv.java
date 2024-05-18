package org.karaffe.compiler.util;

import java.io.PrintStream;
import java.util.*;

public class StartupEnv {

  private String[] commandLineArgs;
  private final Map<String, String> environmentVariables;
  private final Locale locale;

  private StartupEnv(String[] commandLineArgs, Map<String, String> environmentVariables) {
    this.commandLineArgs = Objects.requireNonNull(commandLineArgs);
    this.environmentVariables = Objects.requireNonNull(environmentVariables);
    this.locale = Locale.getDefault();
  }

  public static StartupEnv create(String[] commandLineArgs, Map<String, String> environmentVariables) {
    return new StartupEnv(commandLineArgs, environmentVariables);
  }

  public String[] getCommandLineArgs() {
    return Arrays.copyOf(this.commandLineArgs, this.commandLineArgs.length);
  }

  public Optional<String> getEnv(String key) {
    if (environmentVariables.containsKey(key)) {
      return Optional.of(environmentVariables.get(key));
    } else {
      return Optional.empty();
    }
  }
}
