package org.karaffe.compiler.launcher.tasks;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.Errors;
import org.karaffe.compiler.base.task.NoDescriptionTask;
import org.karaffe.compiler.base.task.Task;
import org.karaffe.compiler.base.task.TaskResult;
import org.karaffe.compiler.frontend.karaffe.tasks.AbstractReadOnlyTask;
import org.karaffe.compiler.launcher.KaraffeCompilerLauncher;

import java.util.stream.Stream;

public class TaskNameCheckTask extends AbstractReadOnlyTask implements NoDescriptionTask {

    @Override
    public String name() {
        return "phase-name check";
    }

    @Override
    public TaskResult run(CompilerContext context) {
        String stopTaskName = context.getCmdLineOptions().stopTaskName;
        if (isUnknownTaskName(stopTaskName)) {
            Errors.invalidTaskName(stopTaskName);
            return TaskResult.FAILED;
        }
        return TaskResult.SUCCESS;
    }

    private boolean isUnknownTaskName(String taskName) {
        if (taskName == null || taskName.isEmpty()) {
            return false;
        }
        return Stream
                .concat(KaraffeCompilerLauncher.execTaskList.stream(),
                        KaraffeCompilerLauncher.standByTaskList.stream())
                .map(Task::name)
                .noneMatch(n -> n.equals(taskName));
    }
}
