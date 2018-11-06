package org.karaffe.compiler.launcher;

import org.karaffe.compiler.base.task.TaskResult;
import org.karaffe.compiler.base.util.Platform;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.PrintStream;

public class KaraffeCompilerLauncher {

    private final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(KaraffeCompilerLauncher.class);

    public KaraffeCompilerLauncher(InputStream stdIn, PrintStream stdOut, PrintStream stdErr) {
        Platform.setStdIn(stdIn);
        Platform.setStdOut(stdOut);
        Platform.setStdErr(stdErr);
    }

    public KaraffeCompilerLauncher() {
        this(System.in, System.out, System.err);
    }

    public static void main(String[] args) throws Exception {
        KaraffeCompilerLauncher launcher = new KaraffeCompilerLauncher();
        int exit = launcher.run(args);
        System.exit(exit);
    }

    public int run(String[] args) throws Exception {
        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
            Platform.stdErr("Uncaught Exception");
            Platform.stdErr("ThreadName: " + thread.getName());
            if (throwable != null) {
                throwable.printStackTrace(Platform.getStdErr());
            }
        });
        KaraffeCompiler compiler = new KaraffeCompiler(args);
        TaskResult compilerResult = compiler.run();
        return compilerResult.isSuccessful() ? 0 : -1;
    }

}
