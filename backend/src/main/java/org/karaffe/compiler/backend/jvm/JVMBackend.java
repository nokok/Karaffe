package org.karaffe.compiler.backend.jvm;

import org.karaffe.compiler.backend.jvm.tasks.DefaultConstructorTask;
import org.karaffe.compiler.backend.jvm.tasks.GenJavaByteCodeTask;
import org.karaffe.compiler.backend.jvm.tasks.InsertDefaultImportTask;
import org.karaffe.compiler.backend.jvm.tasks.TypeCheckTask;
import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.task.AbstractTask;
import org.karaffe.compiler.base.task.RunnerResult;
import org.karaffe.compiler.base.task.Task;
import org.karaffe.compiler.base.task.TaskResult;
import org.karaffe.compiler.base.task.TaskRunner;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class JVMBackend extends AbstractTask {

    private static final Set<Task> subTask = new LinkedHashSet<>(Arrays.asList(
            new InsertDefaultImportTask(),
            new DefaultConstructorTask(),
            new TypeCheckTask(),
            new GenJavaByteCodeTask()));

    @Override
    public String name() {
        return "jvm-backend";
    }

    @Override
    public String description() {
        return "Backend implementation for Java VM";
    }

    @Override
    public TaskResult run(CompilerContext context) {
        TaskRunner taskRunner = TaskRunner.newDefaultTaskRunner(context);
        getSubTask(context).forEach(taskRunner::standBy);
        RunnerResult runnerResult = taskRunner.runAll();
        return runnerResult.toTaskResult();
    }

    @Override
    public Set<Task> getSubTask(CompilerContext context) {
        return subTask;
    }

    @Override
    public boolean changed() {
        return true;
    }
}
