package org.karaffe.compiler.launcher;

import org.karaffe.compiler.backend.jvm.BackendType;
import org.karaffe.compiler.backend.jvm.KaraffeComilerBackend;
import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.CompilerContextImpl;
import org.karaffe.compiler.base.task.RunnerResult;
import org.karaffe.compiler.base.task.Task;
import org.karaffe.compiler.base.task.TaskRunner;
import org.karaffe.compiler.base.util.Platform;
import org.karaffe.compiler.frontend.karaffe.FrontendType;
import org.karaffe.compiler.frontend.karaffe.KaraffeCompilerFrontend;
import org.karaffe.compiler.frontend.karaffe.tasks.ConfigureLogLevelTask;
import org.karaffe.compiler.frontend.karaffe.tasks.options.CommandLineOptionsSubTask;
import org.karaffe.compiler.frontend.karaffe.tasks.options.ParseCommandLineOptionsTask;
import org.karaffe.compiler.frontend.karaffe.tasks.preconditions.CheckCompilerPreconditionTask;
import org.karaffe.compiler.launcher.tasks.ShowDiagnosticInfoTask;
import org.karaffe.compiler.launcher.tasks.ShowUsageTask;
import org.karaffe.compiler.launcher.tasks.ShowVersionTask;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.LinkedHashSet;
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

    private static final Set<Task> taskList = new LinkedHashSet<>(Arrays.asList(
            new ParseCommandLineOptionsTask(),
            new ConfigureLogLevelTask(),
            new CheckCompilerPreconditionTask(),
            new CommandLineOptionsSubTask()
    ));

    private static final Set<Task> standByTaskList = new LinkedHashSet<>(Arrays.asList(
            new ShowDiagnosticInfoTask(),
            new ShowUsageTask(),
            new ShowVersionTask()
    ));

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
        CompilerContext context = new CompilerContextImpl(args);

        TaskRunner taskRunner = TaskRunner.newDefaultTaskRunner(context);
        Runnable failedAction = context::setInvalidCmdLineArg;
        for (Task task : taskList) {
            taskRunner.exec(task).ifFailed(failedAction);
        }

        for (Task task : standByTaskList) {
            taskRunner.standBy(task);
        }
        RunnerResult runnerResult = taskRunner.runAll();
        if (runnerResult != RunnerResult.SUCCESS_ALL) {
            return -1;
        }

        KaraffeCompilerFrontend frontend = KaraffeCompilerFrontend.getFrontend(FrontendType.KARAFFE);
        KaraffeComilerBackend backend = KaraffeComilerBackend.getBackend(BackendType.JVM);
        return frontend.exec(context).map(backend::exec).orElse(-1);
    }

}
