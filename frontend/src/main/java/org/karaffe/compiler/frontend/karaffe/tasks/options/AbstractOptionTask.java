package org.karaffe.compiler.frontend.karaffe.tasks.options;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.task.AbstractTask;
import org.karaffe.compiler.base.task.NoDescriptionTask;
import org.karaffe.compiler.base.task.TaskResult;
import org.karaffe.compiler.base.util.config.Options;

public abstract class AbstractOptionTask extends AbstractTask implements NoDescriptionTask {

    @Override
    public TaskResult run(CompilerContext context) {
        return run(context.getCmdLineOptions(), context);
    }

    public abstract TaskResult run(Options options, CompilerContext context);

    @Override
    public boolean changed() {
        return false;
    }

}
