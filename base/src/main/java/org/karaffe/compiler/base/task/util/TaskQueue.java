package org.karaffe.compiler.base.task.util;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.task.Task;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;

public class TaskQueue {

    private final ArrayDeque<Task> taskQueue;

    public TaskQueue() {
        this(new ArrayList<>(0));
    }

    public TaskQueue(Collection<? extends Task> tasks) {
        this.taskQueue = new ArrayDeque<>(tasks);
    }

    public boolean hasRemaining() {
        return !this.taskQueue.isEmpty();
    }

    public boolean isEmpty() {
        return this.taskQueue.isEmpty();
    }

    public Task dequeue() {
        return this.taskQueue.remove();
    }

    public void addFirst(Task task) {
        this.taskQueue.addFirst(task);
    }

    public void mergeFirst(TaskQueue other) {
        other.taskQueue.forEach(this::addFirst);
        other.clear();
    }

    public void clear() {
        this.taskQueue.clear();
    }

    public void addLast(Task task) {
        this.taskQueue.addLast(task);
    }

    public boolean hasRemainingRequiredTask(CompilerContext context) {
        return this.taskQueue.stream().anyMatch(t -> t.isRequired(context));
    }
}
