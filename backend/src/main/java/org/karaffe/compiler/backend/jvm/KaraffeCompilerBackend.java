package org.karaffe.compiler.backend.jvm;

import org.karaffe.compiler.base.task.Task;

public interface KaraffeCompilerBackend {

    public static Task getBackend(BackendType type) {
        if (type == BackendType.JVM) {
            return new JVMBackend();
        }
        throw new UnsupportedOperationException();
    }
}
