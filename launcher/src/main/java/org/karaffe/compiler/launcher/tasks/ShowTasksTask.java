package org.karaffe.compiler.launcher.tasks;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.task.Task;
import org.karaffe.compiler.base.task.TaskResult;
import org.karaffe.compiler.base.util.Platform;
import org.karaffe.compiler.frontend.karaffe.tasks.AbstractReadOnlyTask;
import org.karaffe.compiler.launcher.KaraffeCompilerLauncher;

import java.util.LinkedHashSet;
import java.util.Set;

public class ShowTasksTask extends AbstractReadOnlyTask {

    @Override
    public String name() {
        return "show tasks";
    }

    @Override
    public String description() {
        return "Show all available tasks";
    }

    @Override
    public TaskResult run(CompilerContext context) {
        Set<Task> showTasks = new LinkedHashSet<>();
        showTasks.addAll(KaraffeCompilerLauncher.execTaskList);
        showTasks.addAll(KaraffeCompilerLauncher.standByTaskList);

        int maxLengthTaskName = calcHeaderLength(showTasks, context);

        Platform.stdOut(String.format("%" + maxLengthTaskName + "s | description", "task name"));
        for (Task task : showTasks) {
            printTaskRow(maxLengthTaskName, task, context);
        }

        return TaskResult.SUCCESS;
    }

    private int calcHeaderLength(Set<Task> tasks, CompilerContext context) {
        int max = 0;
        for (Task task : tasks) {
            max = Math.max(max, calcHeaderLength(task, context));
        }
        return max;
    }

    private int calcHeaderLength(Task task, CompilerContext context) {
        int max = 0;
        max = Math.max(max, task.name().length());
        for (Task subTask : task.getSubTask(context)) {
            max = Math.max(max, subTask.name().length());
        }
        return max;
    }

    private void printTaskRow(int headerLength, Task task, CompilerContext context) {
        Platform.stdOut(String.format("%" + headerLength + "s | %s", task.name(), task.description()));
        Set<Task> subTaskList = task.getSubTask(context);
        for (Task subTask : subTaskList) {
            printTaskRow(headerLength, subTask, context);
        }
    }

    @Override
    public boolean isRunnable(CompilerContext context) {
        return context.getCmdLineOptions().showTasks;
    }
}
