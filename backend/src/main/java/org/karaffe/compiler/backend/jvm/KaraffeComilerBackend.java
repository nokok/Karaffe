package org.karaffe.compiler.backend.jvm;

import org.karaffe.compiler.base.CompilerContext;

public interface KaraffeComilerBackend {

    int exec(CompilerContext instructions);

    public static KaraffeComilerBackend getBackend(BackendType type) {
        if (type == BackendType.JVM) {
            return new JVMBackend();
        }
        throw new UnsupportedOperationException();
    }
}
