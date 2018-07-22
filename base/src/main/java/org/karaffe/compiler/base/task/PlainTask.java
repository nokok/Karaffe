package org.karaffe.compiler.base.task;

import org.karaffe.compiler.base.CompilerContext;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class PlainTask implements Task, NoDescriptionTask {

    private final String taskName;
    private String description = "";
    private List<Task> tasks = new ArrayList<>();

    public PlainTask(String taskName) {
        this.taskName = Objects.requireNonNull(taskName);
    }

    @Override
    public String name() {
        return this.taskName;
    }


    @Override
    public String description() {
        if (this.description.isEmpty()) {
            return NoDescriptionTask.super.description();
        }
        return this.description;
    }

    public void setDescription(String description) {
        this.description = Objects.requireNonNull(description);
    }

    @Override
    public TaskResult run(CompilerContext context) {
        if (tasks.isEmpty()) {
            return TaskResult.SUCCESSFUL;
        }
        TaskRunner taskRunner = TaskRunner.newDefaultTaskRunner(context);
        this.tasks.forEach(taskRunner::standBy);
        return taskRunner.runAll().toTaskResult();
    }

    public void addTask(Task task) {
        this.tasks.add(Objects.requireNonNull(task));
    }

    @Override
    public Set<Task> getSubTask(CompilerContext context) {
        return new LinkedHashSet<>(this.tasks);
    }

    @Override
    public boolean changed() {
        return false;
    }
}
