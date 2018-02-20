package org.karaffe.compiler;

import org.karaffe.compiler.context.CompilerConfig;

public final class CompilerFactory {
    public static KaraffeCompiler createCompiler(final String[] args) {
        return new KaraffeCompiler(CompilerConfig.buildConfig(args));
    }
}
