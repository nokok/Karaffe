package org.karaffe.compiler.launcher;

import org.karaffe.compiler.backend.jvm.KaraffeCompilerBackend;
import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.CompilerContextImpl;
import org.karaffe.compiler.base.report.Report;
import org.karaffe.compiler.base.task.RunnerResult;
import org.karaffe.compiler.base.task.Task;
import org.karaffe.compiler.base.task.TaskResult;
import org.karaffe.compiler.base.task.TaskRunner;
import org.karaffe.compiler.base.util.Platform;
import org.karaffe.compiler.base.util.SourceFile;
import org.karaffe.compiler.base.util.TaskListPrinter;
import org.karaffe.compiler.frontend.karaffe.KaraffeCompilerFrontend;
import org.karaffe.compiler.frontend.karaffe.tasks.ConfigureLogLevelTask;
import org.karaffe.compiler.frontend.karaffe.tasks.options.CommandLineOptionsSubTask;
import org.karaffe.compiler.frontend.karaffe.tasks.options.ParseCommandLineOptionsTask;
import org.karaffe.compiler.frontend.karaffe.tasks.preconditions.CheckCompilerPreconditionTask;
import org.karaffe.compiler.launcher.tasks.ShowDiagnosticInfoTask;
import org.karaffe.compiler.launcher.tasks.ShowUsageTask;
import org.karaffe.compiler.launcher.tasks.ShowVersionTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class KaraffeCompiler {
    private final CompilerContext context;
    private static final Logger LOGGER = LoggerFactory.getLogger(KaraffeCompiler.class);

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


    public KaraffeCompiler() {
        this(new CompilerContextImpl());
    }

    public KaraffeCompiler(String source, String... args) {
        this.context = new CompilerContextImpl(args);
        File tempFile = null;
        try {
            tempFile = File.createTempFile("karaffe-auto", ".krf");
            Files.write(tempFile.toPath(), source.getBytes());
            this.context.addSourceFile(new SourceFile(tempFile));

        } catch (IOException e) {
            throw new UncheckedIOException(e);
        } finally {
            if (tempFile != null) {
                tempFile.delete();
            }
        }
    }

    public KaraffeCompiler(String[] args) {
        this(new CompilerContextImpl(args));
    }

    public KaraffeCompiler(CompilerContext context) {
        this.context = Objects.requireNonNull(context);
    }

    public TaskResult run() throws Exception {
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
            LOGGER.warn("Unsuccessful");
            return runnerResult.toTaskResult();
        }

        LOGGER.debug("PreExectask executed");
        taskRunner.clear();

        taskRunner.standBy(KaraffeCompilerFrontend.getFrontend(context));
        taskRunner.standBy(KaraffeCompilerBackend.getBackend(context));

        if (context.getCmdLineOptions().showTasks) {
            TaskListPrinter printer = new TaskListPrinter();
            Platform.print(printer.format(context, taskRunner.getTasks()));
        }

        RunnerResult compilerResult = taskRunner.runAll();

        if (context.getCmdLineOptions().dumpMIR) {
            Platform.stdOut(context.getIR());
        }

        if (context.hasErrorReport()) {
            for (Report report : context.getReports()) {
                Platform.stdErr(report);
            }
            LOGGER.error("hasErrorReport == true");
            return TaskResult.FAILED;
        }
        for (Map.Entry<Path, byte[]> entry : context.getBytecodes().entrySet()) {
            LOGGER.debug("Write to : {}", entry.getKey());
            Files.write(entry.getKey(), entry.getValue());
        }

        return compilerResult.toTaskResult();
    }

    public static KaraffeCompiler newCompilerWithDebug(String source) {
        return new KaraffeCompiler(source, "--debug");
    }
}
