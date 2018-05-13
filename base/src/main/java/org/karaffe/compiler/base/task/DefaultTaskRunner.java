package org.karaffe.compiler.base.task;

import org.karaffe.compiler.base.CompilerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class DefaultTaskRunner implements TaskRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultTaskRunner.class);

    private final CompilerContext context;
    private final List<Task> tasks = new ArrayList<>();
    private boolean isExecuting = false;
    private final List<Task> delayedTasks = new ArrayList<>();

    public DefaultTaskRunner(CompilerContext context) {
        this.context = context;
    }

    @Override
    public void standBy(Task task) {
        LOGGER.debug("standBy : {}", task);
        if (this.isExecuting) {
            this.delayedTasks.add(task);
        } else {
            this.tasks.add(task);
        }
    }

    @Override
    public TaskResult exec(Task task) {
        LOGGER.debug("Executing...(Immediate) : " + task.name());
        TaskResult result = task.run(context);
        LOGGER.debug("Executing complete : {}", result);
        return result;
    }

    @Override
    public RunnerResult runAll() {
        isExecuting = true;
        int success = 0;
        int warn = 0;
        Queue<Task> taskQueue = new ArrayDeque<>(this.tasks);
        while (!taskQueue.isEmpty()) {
            Task task = taskQueue.poll();
            if (!task.isRunnable(context)) {
                this.delayedTasks.add(task);
                if (taskQueue.isEmpty()) {
                    // タスクキューが空の場合、CompilerContextの状態が変更されることはもう無いため、このタスクは実行可能状態となることはない。
                    return RunnerResult.FAILED;
                }
                continue;
            }
            TaskResult result = task.run(context);
            if (result == TaskResult.FAILED) {
                return RunnerResult.FAILED;
            }
            if (result == TaskResult.SUCCESS) {
                success++;
            } else if (result == TaskResult.SUCCESS_WITH_WARN) {
                warn++;
            }
            this.delayedTasks.forEach(((ArrayDeque<Task>) taskQueue)::addFirst);
            this.delayedTasks.clear();
        }
        isExecuting = false;

        if (success >= 0 && warn == 0) {
            return RunnerResult.SUCCESS_ALL;
        } else if (success > 0 && warn > 0) {
            return RunnerResult.SUCCESS_WITH_WARNING;
        }
        throw new IllegalStateException(String.format("success=%d, warn=%d", success, warn));
    }

    @Override
    public String toString() {
        int maxTaskNameLength = this.tasks.stream().map(Task::name).max(Comparator.comparingInt(String::length)).orElse("").length();
        List<String> tasks = this.tasks.stream().map(t -> String.format("%" + maxTaskNameLength + "s : %s", t.name(), t.description())).collect(Collectors.toList());
        return String.join("\n", tasks);
    }
}
