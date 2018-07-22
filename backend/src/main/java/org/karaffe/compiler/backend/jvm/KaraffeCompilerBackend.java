package org.karaffe.compiler.backend.jvm;

import org.karaffe.compiler.base.BackendType;
import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.task.Task;

public interface KaraffeCompilerBackend {

    public static Task getBackend(CompilerContext context) {
        if (context.getTargetBackendType() == BackendType.JVM) {
            return new JVMBackend();
        }
        throw new UnsupportedOperationException();
    }
}
