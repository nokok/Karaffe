package org.karaffe.compiler;

import org.karaffe.compiler.util.StartupEnv;
import org.karaffe.compiler.phase.Phases;
import org.karaffe.compiler.tree.formatter.SimpleTreeFormatter;
import org.karaffe.compiler.util.CompilerContext;

public class Main {

  public static void main(String[] args) {
    Main main = new Main();
    main.run(args);
  }

  public void run(String[] args) {
    StartupEnv env = StartupEnv.create(args, System.getenv());
    CompilerContext context = CompilerContext.createInitialContext(env);
    Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
      System.out.println("===ERROR===");
      throwable.printStackTrace();
      if (context == null) {
        return;
      }
      SimpleTreeFormatter formatter = new SimpleTreeFormatter();
      System.out.println(formatter.format(context));
    });

    Phases phases = Phases.createPhasesFromContext(context);
    phases.executeAll();
  }

}
