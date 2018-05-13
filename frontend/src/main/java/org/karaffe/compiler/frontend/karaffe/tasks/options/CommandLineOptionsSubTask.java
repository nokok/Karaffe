package org.karaffe.compiler.frontend.karaffe.tasks.options;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.task.RunnerResult;
import org.karaffe.compiler.base.task.TaskResult;
import org.karaffe.compiler.base.task.TaskRunner;
import org.karaffe.compiler.base.util.SourceFile;
import org.karaffe.compiler.frontend.karaffe.tasks.AbstractReadOnlyTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class CommandLineOptionsSubTask extends AbstractReadOnlyTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandLineOptionsSubTask.class);

    @Override
    public String name() {
        return "command line options";
    }

    @Override
    public String description() {
        return "";
    }

    @Override
    public TaskResult run(CompilerContext context) {
        TaskRunner subTaskRunner = TaskRunner.newDefaultTaskRunner(context);
        Runnable failedAction = context::setInvalidCmdLineArg;
        subTaskRunner.standBy(CheckLogLevelTask::new);
        subTaskRunner.standBy(CheckFileTask::new);
        subTaskRunner.standBy(CheckTargetTask::new);
        RunnerResult result = subTaskRunner.runAll();
        TaskResult tResult = result.toTaskResult();
        tResult.ifFailed(failedAction);

        context.getCmdLineOptions().arguments.stream().map(File::new).map(SourceFile::new).forEach(context::addSourceFile);
        return tResult;
    }
}
