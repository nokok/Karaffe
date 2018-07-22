package org.karaffe.compiler.launcher;

import org.karaffe.compiler.backend.jvm.KaraffeCompilerBackend;
import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.CompilerContextImpl;
import org.karaffe.compiler.base.mir.Instructions;
import org.karaffe.compiler.base.report.Report;
import org.karaffe.compiler.base.task.RunnerResult;
import org.karaffe.compiler.base.task.Task;
import org.karaffe.compiler.base.task.TaskRunner;
import org.karaffe.compiler.base.util.Platform;
import org.karaffe.compiler.base.util.TaskListPrinter;
import org.karaffe.compiler.frontend.karaffe.KaraffeCompilerFrontend;
import org.karaffe.compiler.frontend.karaffe.tasks.ConfigureLogLevelTask;
import org.karaffe.compiler.frontend.karaffe.tasks.options.CommandLineOptionsSubTask;
import org.karaffe.compiler.frontend.karaffe.tasks.options.ParseCommandLineOptionsTask;
import org.karaffe.compiler.frontend.karaffe.tasks.preconditions.CheckCompilerPreconditionTask;
import org.karaffe.compiler.launcher.tasks.ShowDiagnosticInfoTask;
import org.karaffe.compiler.launcher.tasks.ShowUsageTask;
import org.karaffe.compiler.launcher.tasks.ShowVersionTask;
import org.karaffe.compiler.transform.KaraffeTransformer;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Map;
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

    private static final Set<Task> preExecTaskList = new LinkedHashSet<>(Arrays.asList(
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

        LOGGER.debug("Executing PreExectask");

        TaskRunner taskRunner = TaskRunner.newDefaultTaskRunner(context);
        Runnable failedAction = context::setInvalidCmdLineArg;
        for (Task task : preExecTaskList) {
            taskRunner.exec(task).ifFailed(failedAction);
        }

        for (Task task : standByTaskList) {
            taskRunner.standBy(task);
        }
        RunnerResult runnerResult = taskRunner.runAll();
        if (runnerResult != RunnerResult.SUCCESS_ALL) {
            return -1;
        }

        LOGGER.debug("PreExectask executed");
        taskRunner.clear();

        taskRunner.standBy(KaraffeCompilerFrontend.getFrontend(context));
        taskRunner.standBy(KaraffeTransformer.getTransformer(context));
        taskRunner.standBy(KaraffeCompilerBackend.getBackend(context));

        if (context.getCmdLineOptions().showTasks) {
            TaskListPrinter printer = new TaskListPrinter();
            Platform.print(printer.format(context, taskRunner.getTasks()));
        }

        RunnerResult compilerResult = taskRunner.runAll();

        if (context.hasErrorReport()) {
            for (Report report : context.getReports()) {
                Platform.stdErr(report);
            }
            return -1;
        }

        if (context.getCmdLineOptions().dumpMIR) {
            Platform.stdOut(context.getInstructions());
        }

        for (Map.Entry<Path, byte[]> entry : context.getBytecodes()) {
            LOGGER.debug("Write to : {}", entry.getKey());
            Files.write(entry.getKey(), entry.getValue());
        }

        return compilerResult == RunnerResult.SUCCESS_ALL ? 0 : -1;
    }

}
