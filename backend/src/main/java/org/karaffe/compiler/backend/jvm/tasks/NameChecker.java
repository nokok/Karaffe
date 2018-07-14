package org.karaffe.compiler.backend.jvm.tasks;

import org.karaffe.compiler.base.mir.Instructions;
import org.karaffe.compiler.base.task.AbstractTask;
import org.karaffe.compiler.base.task.BackendTask;
import org.karaffe.compiler.base.task.TaskResult;

public class NameChecker extends AbstractTask implements BackendTask {
    @Override
    public TaskResult run(Instructions instructions) {
        return null;
    }

    @Override
    public String name() {
        return null;
    }

    @Override
    public String description() {
        return null;
    }

    @Override
    public boolean changed() {
        return false;
    }
}
