package org.karaffe.compiler.launcher;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.task.RunnerResult;
import org.karaffe.compiler.base.task.Task;
import org.karaffe.compiler.base.task.TaskRunner;
import org.karaffe.compiler.base.util.Platform;
import org.karaffe.compiler.frontend.karaffe.tasks.ConfigureLogLevelTask;
import org.karaffe.compiler.frontend.karaffe.tasks.LexerTask;
import org.karaffe.compiler.frontend.karaffe.tasks.ShowDiagnosticInfoTask;
import org.karaffe.compiler.frontend.karaffe.tasks.ShowUsageTask;
import org.karaffe.compiler.frontend.karaffe.tasks.ShowVersionTask;
import org.karaffe.compiler.frontend.karaffe.tasks.options.CommandLineOptionsSubTask;
import org.karaffe.compiler.frontend.karaffe.tasks.options.ParseCommandLineOptionsTask;
import org.karaffe.compiler.frontend.karaffe.tasks.preconditions.CheckCompilerPreconditionTask;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ServiceLoader;

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
        CompilerContext context = new CompilerContext(args);

        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
            Platform.stdErr("Uncaught Exception");
            Platform.stdErr("ThreadName: " + thread.getName());
            if (throwable != null) {
                throwable.printStackTrace(Platform.getStdErr());
            }
        });

        TaskRunner taskRunner = TaskRunner.newDefaultTaskRunner(context);
        ServiceLoader<Task> taskServiceLoader = ServiceLoader.load(Task.class, Thread.currentThread().getContextClassLoader());
        taskServiceLoader.forEach(taskRunner::standBy);

        Runnable failedAction = context::setInvalidCmdLineArg;

        taskRunner.exec(ParseCommandLineOptionsTask::new).ifFailed(failedAction);
        taskRunner.exec(ConfigureLogLevelTask::new).ifFailed(failedAction);
        taskRunner.exec(CheckCompilerPreconditionTask::new).ifFailed(failedAction);
        taskRunner.exec(CommandLineOptionsSubTask::new).ifFailed(failedAction);

        taskRunner.standBy(ShowDiagnosticInfoTask::new);
        taskRunner.standBy(ShowUsageTask::new);
        taskRunner.standBy(ShowVersionTask::new);
        taskRunner.standBy(LexerTask::new);

        RunnerResult result = taskRunner.runAll();
        return result == RunnerResult.SUCCESS_ALL ? 0 : -1;
    }

}
