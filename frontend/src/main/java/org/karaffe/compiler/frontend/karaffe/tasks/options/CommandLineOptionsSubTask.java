package org.karaffe.compiler.frontend.karaffe.tasks.options;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.task.AbstractTask;
import org.karaffe.compiler.base.task.ReadOnlyTask;
import org.karaffe.compiler.base.task.RunnerResult;
import org.karaffe.compiler.base.task.Task;
import org.karaffe.compiler.base.task.TaskResult;
import org.karaffe.compiler.base.task.TaskRunner;
import org.karaffe.compiler.base.util.SourceFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class CommandLineOptionsSubTask extends AbstractTask implements ReadOnlyTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandLineOptionsSubTask.class);
    private Set<Task> subTask;

    @Override
    public String name() {
        return "post precondition";
    }

    @Override
    public String description() {
        return "Check other arguments";
    }

    @Override
    public Set<Task> getSubTask(CompilerContext context) {
        if (this.subTask != null) {
            return Collections.unmodifiableSet(this.subTask);
        }
        this.subTask = new LinkedHashSet<>();
        this.subTask.add(new CheckLogLevelTask());
        this.subTask.add(new CheckFileTask());
        this.subTask.add(new CheckTargetTask());
        return Collections.unmodifiableSet(this.subTask);
    }

    @Override
    public TaskResult run(CompilerContext context) {
        TaskRunner subTaskRunner = TaskRunner.newDefaultTaskRunner(context);
        this.getSubTask(context).forEach(subTaskRunner::standBy);
        RunnerResult result = subTaskRunner.runAll();
        TaskResult tResult = result.toTaskResult();
        tResult.ifSuccess(() -> context.getCmdLineOptions().arguments.stream().map(File::new).map(SourceFile::new).forEach(context::addSourceFile));
        return tResult;
    }
}
