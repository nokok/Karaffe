package org.karaffe.compiler.frontend.karaffe.tasks;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.frontend.karaffe.transformer.util.Result;

public class CheckCompilerPrecondition implements Task {

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
            Class.forName("karaffe.core.Any");
            Package pkg = Package.getPackage("karaffe.core");
            if (pkg == null) {
                throw new NullPointerException("karaffe.core package not found.");
            }
            return Result.SUCCESS;
        } catch (ClassNotFoundException e) {
            return Result.NON_RECOVERABLE;
        }
    }

    @Override
    public boolean isRunnable() {
        return true;
    }

    @Override
    public boolean changed() {
        return false;
    }
}
