package org.karaffe.compiler.frontend.karaffe.tasks;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.frontend.karaffe.transformer.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CheckCompilerPrecondition implements Task {

    private static final Logger LOGGER = LoggerFactory.getLogger(CheckCompilerPrecondition.class);

    @Override
    public String name() {
        return "compiler-precondition";
    }

    @Override
    public String description() {
        return "Check precondition";
    }

    @Override
    public Result run(CompilerContext context) {
        try {
            LOGGER.trace("Check Package : {}", "karaffe.core");
            Class.forName("karaffe.core.Any");
            Package pkg = Package.getPackage("karaffe.core");
            if (pkg == null) {
                throw new NullPointerException("karaffe.core package not found.");
            }
            LOGGER.trace("OK");
            return Result.SUCCESS;
        } catch (ClassNotFoundException e) {
            return Result.NON_RECOVERABLE;
        }
    }

    @Override
    public boolean isRunnable(CompilerContext context) {
        return true;
    }

    @Override
    public boolean changed() {
        return false;
    }
}
