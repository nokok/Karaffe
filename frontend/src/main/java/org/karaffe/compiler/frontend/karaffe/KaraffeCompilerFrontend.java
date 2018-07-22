package org.karaffe.compiler.frontend.karaffe;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.FrontendType;
import org.karaffe.compiler.base.task.Task;

public interface KaraffeCompilerFrontend {

    static Task getFrontend(CompilerContext context) {
        if (context.getFrontendType() == FrontendType.KARAFFE) {
            return new KaraffeSourceFrontend();
        }
        throw new UnsupportedOperationException();
    }
}
