package org.karaffe.compiler.frontend.karaffe;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.mir.Instructions;

import java.util.Optional;

public interface KaraffeCompilerFrontend {
    Optional<Instructions> exec(CompilerContext container);

    public static KaraffeCompilerFrontend getFrontend(FrontendType type) {
        if (type == FrontendType.KARAFFE) {
            return new KaraffeSourceFrontend();
        }
        throw new UnsupportedOperationException();
    }
}
