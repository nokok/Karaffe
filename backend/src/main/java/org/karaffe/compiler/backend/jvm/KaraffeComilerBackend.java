package org.karaffe.compiler.backend.jvm;

import org.karaffe.compiler.base.mir.Instructions;

public interface KaraffeComilerBackend {

    int exec(Instructions instructions);

    public static KaraffeComilerBackend getBackend(BackendType type) {
        if (type == BackendType.JVM) {
            return new JVMBackend();
        }
        throw new UnsupportedOperationException();
    }
}
