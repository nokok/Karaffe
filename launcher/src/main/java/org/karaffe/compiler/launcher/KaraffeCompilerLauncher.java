package org.karaffe.compiler.launcher;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.task.RunnerResult;
import org.karaffe.compiler.base.task.Task;
import org.karaffe.compiler.base.task.TaskRunner;
import org.karaffe.compiler.base.util.Platform;
import org.karaffe.compiler.frontend.karaffe.tasks.ConfigureLogLevelTask;
import org.karaffe.compiler.frontend.karaffe.tasks.LexerTask;
import org.karaffe.compiler.frontend.karaffe.tasks.ParserTask;
import org.karaffe.compiler.launcher.tasks.ShowDiagnosticInfoTask;
import org.karaffe.compiler.launcher.tasks.ShowPhasesTask;
import org.karaffe.compiler.launcher.tasks.ShowUsageTask;
import org.karaffe.compiler.launcher.tasks.ShowVersionTask;
import org.karaffe.compiler.frontend.karaffe.tasks.options.CommandLineOptionsSubTask;
import org.karaffe.compiler.frontend.karaffe.tasks.options.ParseCommandLineOptionsTask;
import org.karaffe.compiler.frontend.karaffe.tasks.preconditions.CheckCompilerPreconditionTask;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.ServiceLoader;
import java.util.Set;

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

    private static final Set<Class<? extends Task>> execTaskList = new LinkedHashSet<>(Arrays.asList(
            ParseCommandLineOptionsTask.class,
            ConfigureLogLevelTask.class,
            CheckCompilerPreconditionTask.class,
            CommandLineOptionsSubTask.class
    ));

    private static final Set<Class<? extends Task>> standByTaskList = new LinkedHashSet<>(Arrays.asList(
            ShowDiagnosticInfoTask.class,
            ShowUsageTask.class,
            ShowVersionTask.class,
            ShowPhasesTask.class,
            LexerTask.class,
            ParserTask.class
    ));

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
        for (Class<? extends Task> clazz : execTaskList) {
            Task task = clazz.getConstructor().newInstance();
            taskRunner.exec(task).ifFailed(failedAction);
        }

        for (Class<? extends Task> clazz : standByTaskList) {
            Task task = clazz.getConstructor().newInstance();
            taskRunner.standBy(task);
        }

        RunnerResult result = taskRunner.runAll();
        return result == RunnerResult.SUCCESS_ALL ? 0 : -1;
    }

}
