package org.karaffe.compiler.frontend.karaffe.tasks;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.frontend.karaffe.tasks.util.RunnerResult;
import org.karaffe.compiler.frontend.karaffe.tasks.util.TaskResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class DefaultTaskRunner implements TaskRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultTaskRunner.class);

    private final CompilerContext context;
    private final List<Task> tasks = new ArrayList<>();
    private boolean isExecuting = false;
    private final List<Task> addedTasks = new ArrayList<>();

    public DefaultTaskRunner(CompilerContext context) {
        this.context = context;
    }

    @Override
    public void standby(Task task) {
        if (this.isExecuting) {
            LOGGER.debug("Add task(Added) : " + task.name());
            this.addedTasks.add(task);
        } else {
            LOGGER.debug("Add task : " + task.name());
            this.tasks.add(task);
        }
    }

    @Override
    public RunnerResult exec(Task task) {
        LOGGER.debug("Executing...(Immediate) : " + task.name());
        TaskResult result = task.run(context);
        LOGGER.debug("Executing complete : {}", result);
        return result.toRunnerResult();
    }

    @Override
    public RunnerResult runAll() {
        isExecuting = true;
        List<Task> currentTasks = new ArrayList<>();
        currentTasks.addAll(tasks);
        currentTasks.addAll(addedTasks);
        addedTasks.clear();
        List<Task> nextTasks = new ArrayList<>();

        int success = 0;
        int warn = 0;
        boolean hasError = false;
        for (Task task : currentTasks) {
            if (task.isRunnable(context)) {
                LOGGER.debug("Executing... : {}", task.name());
                TaskResult result = task.run(context);
                LOGGER.debug("Executed. TaskResult : {}", result);
                if (result == TaskResult.SUCCESS) {
                    success++;
                }
                if (result == TaskResult.SUCCESS_WITH_WARN) {
                    warn++;
                }
                if (result == TaskResult.NON_RECOVERABLE) {
                    hasError = true;
                    break;
                }
            } else {
                LOGGER.debug("Skipped : {}", task.name());
                nextTasks.add(task);
            }
        }
        this.tasks.clear();
        this.tasks.addAll(nextTasks);
        isExecuting = false;

        if (success >= 0 && warn == 0 && !hasError) {
            return RunnerResult.SUCCESS_ALL;
        } else if (success > 0 && warn > 0 && !hasError) {
            return RunnerResult.SUCCESS_WITH_WARNING;
        }
        if (hasError) {
            return RunnerResult.FAILED;
        } else {
            throw new IllegalStateException(String.format("%s, %s, %s", success, warn, hasError));
        }
    }

}
