package org.karaffe.compiler.frontend.karaffe;

import org.karaffe.compiler.base.task.Task;

public interface KaraffeCompilerFrontend {

    public static Task getFrontend(FrontendType type) {
        if (type == FrontendType.KARAFFE) {
            return new KaraffeSourceFrontend();
        }
        throw new UnsupportedOperationException();
    }
}
