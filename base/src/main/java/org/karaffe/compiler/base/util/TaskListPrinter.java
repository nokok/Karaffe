package org.karaffe.compiler.base.util;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.task.Task;

import java.util.Collection;

public class TaskListPrinter {

    public String format(CompilerContext context, Collection<Task> tasks) {
        StringBuilder builder = new StringBuilder();
        for (Task task : tasks) {
            builder.append(format(context, task));
        }
        return builder.toString();
    }

    private String format(CompilerContext context, Task task) {
        StringBuilder builder = new StringBuilder();
        if (context.getCmdLineOptions().isTraceLog || context.getCmdLineOptions().isDebugLog) {
            builder.append(task.name()).append(" : ").append(task.description()).append(" (").append(task).append(")").append(System.lineSeparator());
        } else {
            builder.append(task.name()).append(" : ").append(task.description()).append(System.lineSeparator());
        }
        for (Task subTask : task.getSubTask(context)) {
            builder.append(format(context, subTask));
        }
        return builder.toString();
    }
}
