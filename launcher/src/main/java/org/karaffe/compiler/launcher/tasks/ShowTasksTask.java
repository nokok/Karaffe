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

        for (Task task : showTasks) {
            printTask("+-", task, context);
        }

        return TaskResult.SUCCESS;
    }

    private void printTask(String header, Task task, CompilerContext context) {
        if (context.getCmdLineOptions().isTraceLog || context.getCmdLineOptions().isDebugLog) {
            Platform.stdOut(header + task.name() + " : " + task.description() + " (" + task + ")");
        } else {
            Platform.stdOut(header + task.name() + " : " + task.description());
        }
        for (Task subTask : task.getSubTask(context)) {
            printTask("|" + header + "-", subTask, context);
        }
    }

    @Override
    public boolean isRunnable(CompilerContext context) {
        return context.getCmdLineOptions().showTasks;
    }
}
