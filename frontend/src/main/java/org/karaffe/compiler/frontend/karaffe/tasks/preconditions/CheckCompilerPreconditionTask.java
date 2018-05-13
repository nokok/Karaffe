package org.karaffe.compiler.frontend.karaffe.tasks.preconditions;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.task.RunnerResult;
import org.karaffe.compiler.base.task.TaskResult;
import org.karaffe.compiler.base.task.TaskRunner;
import org.karaffe.compiler.frontend.karaffe.tasks.AbstractReadOnlyTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CheckCompilerPreconditionTask extends AbstractReadOnlyTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(CheckCompilerPreconditionTask.class);

    @Override
    public String name() {
        return "compiler-precondition";
    }

    @Override
    public String description() {
        return "Check precondition";
    }

    @Override
    public TaskResult run(CompilerContext context) {
        TaskRunner taskRunner = TaskRunner.newDefaultTaskRunner(context);
        if (!context.getCmdLineOptions().skipPackageCheck) {
            taskRunner.standBy(CheckCorePackageTask::new);
        }
        RunnerResult result = taskRunner.runAll();
        return result.toTaskResult();
    }

}
