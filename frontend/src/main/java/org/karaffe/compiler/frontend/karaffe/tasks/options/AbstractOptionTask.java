package org.karaffe.compiler.frontend.karaffe.tasks.options;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.task.NoDescriptionTask;
import org.karaffe.compiler.base.task.TaskResult;
import org.karaffe.compiler.base.util.config.Options;
import org.karaffe.compiler.frontend.karaffe.tasks.AbstractTask;

public abstract class AbstractOptionTask extends AbstractTask implements NoDescriptionTask {

    @Override
    public TaskResult run(CompilerContext context) {
        return run(context.getCmdLineOptions());
    }

    public abstract TaskResult run(Options options);

    @Override
    public boolean changed() {
        return false;
    }

}
