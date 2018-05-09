package org.karaffe.compiler.launcher;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.util.Platform;
import org.karaffe.compiler.frontend.karaffe.tasks.preconditions.CheckCompilerPreconditionTask;
import org.karaffe.compiler.frontend.karaffe.tasks.ConfigureLogLevelTask;
import org.karaffe.compiler.frontend.karaffe.tasks.options.ParseCommandLineOptionsTask;
import org.karaffe.compiler.frontend.karaffe.tasks.ShowDiagnosticInfoTask;
import org.karaffe.compiler.frontend.karaffe.tasks.ShowUsageTask;
import org.karaffe.compiler.frontend.karaffe.tasks.ShowVersionTask;
import org.karaffe.compiler.frontend.karaffe.tasks.Task;
import org.karaffe.compiler.frontend.karaffe.tasks.TaskRunner;
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

    }

    public static void main(String[] args) throws Exception {
        KaraffeCompilerLauncher launcher = new KaraffeCompilerLauncher();
        launcher.run(args);
    }

    public void run(String[] args) throws Exception {
        CompilerContext context = new CompilerContext();
        context.setArgs(args);

        TaskRunner taskRunner = TaskRunner.newDefaultTaskRunner(context);
        ServiceLoader<Task> taskServiceLoader = ServiceLoader.load(Task.class, Thread.currentThread().getContextClassLoader());
        taskServiceLoader.forEach(taskRunner::standby);

        taskRunner.exec(ParseCommandLineOptionsTask::new);
        taskRunner.exec(ConfigureLogLevelTask::new);
        taskRunner.exec(CheckCompilerPreconditionTask::new);

        taskRunner.standBy(ShowDiagnosticInfoTask::new);
        taskRunner.standBy(ShowUsageTask::new);
        taskRunner.standBy(ShowVersionTask::new);

        taskRunner.runAll();
    }

}
