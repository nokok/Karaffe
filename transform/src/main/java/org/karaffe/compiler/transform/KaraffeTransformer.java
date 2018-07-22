package org.karaffe.compiler.transform;

import org.karaffe.compiler.base.BackendType;
import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.FrontendType;
import org.karaffe.compiler.base.task.PlainTask;
import org.karaffe.compiler.base.task.Task;
import org.karaffe.compiler.transform.tasks.TypeCheckTask;

public interface KaraffeTransformer {
    public static Task getTransformer(CompilerContext context) {
        PlainTask task = new PlainTask("transform");
        task.setDescription("Transform MIR");
        FrontendType frontendType = context.getFrontendType();
        BackendType targetBackendType = context.getTargetBackendType();
        task.addTask(new TypeCheckTask());
        return task;
    }
}
