package org.karaffe.compiler.frontend.karaffe.tasks.postmir;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.task.AbstractTask;
import org.karaffe.compiler.base.task.NoDescriptionTask;
import org.karaffe.compiler.base.task.Task;
import org.karaffe.compiler.base.task.TaskResult;
import org.karaffe.compiler.base.task.TaskRunner;

import java.util.LinkedHashSet;
import java.util.Set;

public class PostMIRTask extends AbstractTask implements NoDescriptionTask {

    private boolean isChanged = false;

    private Set<Task> subTask = new LinkedHashSet<>();

    public PostMIRTask() {
        this.subTask.add(new InsertDefaultConstructorTask());
    }

    @Override
    public String name() {
        return "frontend-karaffe-postmir";
    }

    @Override
    public TaskResult run(CompilerContext context) {
        TaskRunner taskRunner = TaskRunner.newDefaultTaskRunner(context);
        this.subTask.forEach(taskRunner::standBy);
        return taskRunner.runAll().toTaskResult();
    }

    @Override
    public Set<Task> getSubTask(CompilerContext context) {
        return this.subTask;
    }

    @Override
    public boolean changed() {
        return this.isChanged;
    }
}
