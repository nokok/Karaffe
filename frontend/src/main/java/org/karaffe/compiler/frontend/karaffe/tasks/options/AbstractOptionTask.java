package org.karaffe.compiler.frontend.karaffe.tasks.options;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.util.config.Options;
import org.karaffe.compiler.frontend.karaffe.tasks.AbstractTask;
import org.karaffe.compiler.frontend.karaffe.tasks.util.TaskResult;

public abstract class AbstractOptionTask extends AbstractTask {

    @Override
    public String description() {
        return "";
    }

    @Override
    public TaskResult run(CompilerContext context) {
        return run(context.cmdLineOptions);
    }

    public abstract TaskResult run(Options options);

    @Override
    public boolean changed() {
        return false;
    }
}
