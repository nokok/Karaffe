package org.karaffe.compiler.base.task;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.task.util.ProcessTimer;
import org.karaffe.compiler.base.task.util.ResultRecorder;
import org.karaffe.compiler.base.task.util.TaskQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultTaskRunner implements TaskRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultTaskRunner.class);

    private final CompilerContext context;
    private final List<Task> tasks = new ArrayList<>();
    private final TaskQueue delayedTasks = new TaskQueue();
    private final TaskQueue finallyTasks = new TaskQueue();
    private boolean isExecuting = false;

    public DefaultTaskRunner(CompilerContext context) {
        this.context = context;
    }

    @Override
    public void standBy(Task task) {
        LOGGER.debug("standBy : {}", task);
        if (this.isExecuting) {
            this.delayedTasks.addLast(task);
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
        ResultRecorder resultRecorder = new ResultRecorder();
        LOGGER.debug("start runAll : {}", this.hashCode());
        TaskQueue queue = new TaskQueue(this.tasks);
        List<Task> repeatable = queue.filter(task -> task.isRepetable(context));

        while (queue.hasRemaining()) {
            Task task = queue.dequeue();
            if (task.isFinally(context)) {
                this.finallyTasks.addFirst(task);
                continue;
            }
            if (task.isRequired(context)) {
                LOGGER.debug("Scheduled(Required) : {}", task.name());
            } else {
                LOGGER.debug("Scheduled : {}", task.name());
            }
            if (!task.isRunnable(context)) {
                this.delayedTasks.addLast(task);
                if (queue.isEmpty()) {
                    // タスクキューが空の場合、CompilerContextの状態が変更されることはもう無いため、遅延されたタスクは実行可能状態となることはない。
                    if (delayedTasks.hasRemainingRequiredTask(context)) {
                        // 必須タスクが残っている場合はエラー
                        LOGGER.warn("RunnerResult.FAILED : taskQueue.isEmpty");
                        runFinallyTask(context, resultRecorder);
                        return RunnerResult.FAILED;
                    } else {
                        LOGGER.info("RunnerResult.SUCCESS : taskQueue.isEmpty");
                        runFinallyTask(context, resultRecorder);
                        return RunnerResult.SUCCESS_ALL;
                    }
                }
                continue;
            }
            ProcessTimer timer = new ProcessTimer();
            TaskResult result = task.run(context);
            LOGGER.info("TaskResult : {}, name : {} [{}ms]", result, task.name(), timer.stop());
            resultRecorder.record(result);
            if (resultRecorder.hasError()) {
                runFinallyTask(context, resultRecorder);
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

        LOGGER.debug("Standard Task(s) is executed.");
        runFinallyTask(context, resultRecorder);
        isExecuting = false;

        LOGGER.debug("end runAll : {}", this.hashCode());

        return resultRecorder.toRunnerResult();
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
