package org.karaffe.compiler.backend.jvm.tasks;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.mir.Instruction;
import org.karaffe.compiler.base.mir.Instructions;
import org.karaffe.compiler.base.task.AbstractTask;
import org.karaffe.compiler.base.task.BackendTask;
import org.karaffe.compiler.base.task.TaskResult;

public class RenameTypeTask extends AbstractTask implements BackendTask {
    @Override
    public TaskResult run(Instructions instructions, CompilerContext context) {
        for (Instruction instruction : instructions) {
            
        }
        return TaskResult.SUCCESS;
    }

    @Override
    public String name() {
        return "jvm-backend-renametype";
    }

    @Override
    public String description() {
        return "Rename from fully qualified class name to Java VM internal name";
    }

    @Override
    public boolean changed() {
        return true;
    }
}
