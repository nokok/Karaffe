package org.karaffe.compiler.base.task;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.task.util.ProcessTimer;
import org.karaffe.compiler.base.task.util.ResultRecorder;
import org.karaffe.compiler.base.task.util.TaskCanceledException;
import org.karaffe.compiler.base.task.util.TaskQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class DefaultTaskRunner implements TaskRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultTaskRunner.class);

    private final CompilerContext context;
    private final List<Task> tasks = new ArrayList<>();
    private final List<Pattern> disables = new ArrayList<>();
    private final TaskQueue delayedTasks = new TaskQueue();
    private final TaskQueue finallyTasks = new TaskQueue();
    private boolean isExecuting = false;

    DefaultTaskRunner(CompilerContext context) {
        this.context = context;
    }

    @Override
    public void standBy(Task task) {
        LOGGER.trace("standBy : {}", task.name());
        if (this.isExecuting) {
            LOGGER.trace("standBy(delayed) : " + task.name());
            this.delayedTasks.addLast(task);
        } else {
            LOGGER.trace("standBy : " + task.name());
            this.tasks.add(task);
        }
    }

    @Override
    public TaskResult exec(Task task) {
        LOGGER.debug("Started  : {}", task.name());
        ProcessTimer timer = new ProcessTimer();
        TaskResult result = task.run(context);
        LOGGER.debug("Executing complete : {}", result);
        LOGGER.info("Executed : {} {} in {}ms", task.name(), result, String.format("%.3f", timer.stop() / 1000000.0d));
        return result;
    }

    @Override
    public RunnerResult runAll() {
        isExecuting = true;
        ResultRecorder resultRecorder = new ResultRecorder();
        LOGGER.trace("start runAll : {}", this.hashCode());
        TaskQueue queue = new TaskQueue(this.tasks);
        List<Task> repeatable = queue.filter(task -> task.isRepetable(context));

        String stopTaskName = context.getCmdLineOptions().stopTaskName;
        if (stopTaskName != null) {
            LOGGER.trace("StopTaskName : {}", stopTaskName);
        }

        while (queue.hasRemaining()) {
            Task task = queue.dequeue();
            if (task.isFinally(context)) {
                this.finallyTasks.addFirst(task);
                LOGGER.trace("Finally Task : " + task);
                continue;
            }
            if (task.isRequired(context)) {
                LOGGER.trace("Scheduled(Required) : {}", task.name());
            } else {
                LOGGER.trace("Scheduled : {}", task.name());
            }
            if (!task.isRunnable(context)) {
                LOGGER.trace("Delayed Task : " + task.name());
                this.delayedTasks.addLast(task);
                if (queue.isEmpty()) {
                    // タスクキューが空の場合、CompilerContextの状態が変更されることはもう無いため、遅延されたタスクは実行可能状態となることはない。
                    if (delayedTasks.hasRemainingRequiredTask(context)) {
                        // 必須タスクが残っている場合はエラー
                        LOGGER.error("RunnerResult.FAILED [delayedTasks.hasRemainingRequiredTask(context) == true]");
                        runFinallyTask(context, resultRecorder);
                        isExecuting = false;
                        return RunnerResult.FAILED;
                    } else {
                        LOGGER.trace("RunnerResult.SUCCESSFUL");
                        runFinallyTask(context, resultRecorder);
                        isExecuting = false;
                        return RunnerResult.SUCCESS_ALL;
                    }
                }
                continue;
            }

            if (this.disables.stream().anyMatch(p -> task.name().matches(p.pattern()))) {
                continue;
            }

            ProcessTimer timer = new ProcessTimer();
            LOGGER.info("Started  : {}", task.name());
            TaskResult result = task.run(context);
            LOGGER.info("Executed : {} {} in {}ms", task.name(), result, String.format("%.3f", timer.stop() / 1000000.0d));

            resultRecorder.record(result);
            if (task.name().equals(stopTaskName)) {
                isExecuting = false;
                throw new TaskCanceledException();
            }
            if (resultRecorder.hasError()) {
                runFinallyTask(context, resultRecorder);
                isExecuting = false;
                return resultRecorder.toRunnerResult();
            }
            if (result == TaskResult.RETRY) {
                this.delayedTasks.addLast(task);
            }
            queue.mergeFirst(this.delayedTasks);
            this.context.setState(task.name());
            for (Task repeatableTask : repeatable) {
                if (repeatableTask.isRunnable(context)) {
                    resultRecorder.record(repeatableTask.run(context));
                }
            }
        }

        LOGGER.trace("Standard Task(s) is executed.");
        runFinallyTask(context, resultRecorder);
        isExecuting = false;

        LOGGER.trace("end runAll : {}", this.hashCode());

        return resultRecorder.toRunnerResult();
    }

    @Override
    public void clear() {
        this.tasks.clear();
        this.delayedTasks.clear();
        this.finallyTasks.clear();
        this.isExecuting = false;
    }

    @Override
    public Set<Task> getTasks() {
        return new LinkedHashSet<>(this.tasks);
    }

    @Override
    public <T extends Task> void disable(String taskPattern) {
        this.disables.add(Pattern.compile(taskPattern));
    }

    private void runFinallyTask(CompilerContext context, ResultRecorder resultRecorder) {
        if (this.finallyTasks.hasRemaining()) {
            LOGGER.debug("Finally Task(s) is executing...");
            while (this.finallyTasks.hasRemaining()) {
                Task task = this.finallyTasks.dequeue();
                if (!task.isRunnable(context)) {
                    continue;
                }
                LOGGER.info("Scheduled : {}", task.name());
                TaskResult result = task.run(context);
                LOGGER.info("TaskResult : {}, name : {}", result, task.name());
                resultRecorder.record(result);
            }
        }
    }

    @Override
    public String toString() {
        int maxTaskNameLength = this.tasks.stream().map(Task::name).max(Comparator.comparingInt(String::length)).orElse("").length();
        List<String> tasks = this.tasks.stream().map(t -> String.format("%" + maxTaskNameLength + "s : %s", t.name(), t.description())).collect(Collectors.toList());
        return String.join("\n", tasks);
    }

}
