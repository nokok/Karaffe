package org.karaffe.compiler.frontend.karaffe.tasks;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.frontend.karaffe.transformer.TaskException;
import org.karaffe.compiler.frontend.karaffe.transformer.util.Result;
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
    public void exec(Task task) {
        LOGGER.debug("Executing...(Immediate) : " + task.name());
        Result result = task.run(context);
        check(result);
    }

    @Override
    public void runAll() {
        isExecuting = true;
        List<Task> currentTasks = new ArrayList<>();
        currentTasks.addAll(tasks);
        currentTasks.addAll(addedTasks);
        addedTasks.clear();
        List<Task> nextTasks = new ArrayList<>();
        currentTasks.forEach(task -> {
            LOGGER.debug("Executing... : {}", task.name());
            if (task.isRunnable(context)) {
                LOGGER.debug("Executing : {}", task.name());
                Result result = task.run(context);
                check(result);
            } else {
                LOGGER.debug("Skipped : {}", task.name());
                nextTasks.add(task);
            }
        });
        this.tasks.clear();
        this.tasks.addAll(nextTasks);
        isExecuting = false;
    }

    private void check(Result result) {
        if (result == Result.NON_RECOVERABLE) {
            throw new TaskException();
        }
    }
}
