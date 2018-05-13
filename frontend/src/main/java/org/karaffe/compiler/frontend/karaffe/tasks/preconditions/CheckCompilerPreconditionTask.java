package org.karaffe.compiler.frontend.karaffe.tasks.preconditions;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.task.RunnerResult;
import org.karaffe.compiler.base.task.Task;
import org.karaffe.compiler.base.task.TaskResult;
import org.karaffe.compiler.base.task.TaskRunner;
import org.karaffe.compiler.frontend.karaffe.tasks.AbstractReadOnlyTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class CheckCompilerPreconditionTask extends AbstractReadOnlyTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(CheckCompilerPreconditionTask.class);
    private Set<Task> subTaskCache;
    private CompilerContext context;

    @Override
    public String name() {
        return "precondition";
    }

    @Override
    public String description() {
        return "Check compiler precondition";
    }

    @Override
    public Set<Task> getSubTask(CompilerContext context) {
        if (this.subTaskCache != null && this.context.equals(context)) {
            return Collections.unmodifiableSet(this.subTaskCache);
        }
        this.subTaskCache = new LinkedHashSet<>();
        this.context = context;
        if (!context.getCmdLineOptions().skipPackageCheck) {
            this.subTaskCache.add(new CheckCorePackageTask());
        }
        return Collections.unmodifiableSet(this.subTaskCache);
    }

    @Override
    public TaskResult run(CompilerContext context) {
        TaskRunner taskRunner = TaskRunner.newDefaultTaskRunner(context);
        this.getSubTask(context).forEach(taskRunner::standBy);
        RunnerResult result = taskRunner.runAll();
        return result.toTaskResult();
    }

}
