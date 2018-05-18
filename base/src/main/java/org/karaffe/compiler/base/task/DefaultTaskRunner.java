package org.karaffe.compiler.base.task;

import org.karaffe.compiler.base.CompilerContext;
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
    private boolean isExecuting = false;
    private final TaskQueue delayedTasks = new TaskQueue();

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
        LOGGER.debug("start runAll");
        TaskQueue queue = new TaskQueue(this.tasks);
        while (queue.hasRemaining()) {
            Task task = queue.dequeue();
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
                        return RunnerResult.FAILED;
                    } else {
                        LOGGER.info("RunnerResult.SUCCESS : taskQueue.isEmpty");
                        return RunnerResult.SUCCESS_ALL;
                    }
                }
                continue;
            }
            TaskResult result = task.run(context);
            LOGGER.info("TaskResult : {}, name : {}", result, task.name());
            resultRecorder.record(result);
            if (resultRecorder.hasError()) {
                return resultRecorder.toRunnerResult();
            }
            if (result == TaskResult.RETRY) {
                this.delayedTasks.addLast(task);
            }
            queue.mergeFirst(this.delayedTasks);
            this.context.setState(task.name());
        }
        isExecuting = false;

        return resultRecorder.toRunnerResult();
    }

    @Override
    public String toString() {
        int maxTaskNameLength = this.tasks.stream().map(Task::name).max(Comparator.comparingInt(String::length)).orElse("").length();
        List<String> tasks = this.tasks.stream().map(t -> String.format("%" + maxTaskNameLength + "s : %s", t.name(), t.description())).collect(Collectors.toList());
        return String.join("\n", tasks);
    }
}
