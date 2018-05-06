package org.karaffe.compiler.frontend.karaffe.phase;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.frontend.karaffe.transformer.util.Result;

public class CompilerPreConditionChecker implements Phase {
    @Override
    public String phaseName() {
        return "compiler-precondition";
    }

    @Override
    public String phaseDescription() {
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
            return Result.FAIL;
        }
    }

    @Override
    public boolean changed() {
        return false;
    }
}
