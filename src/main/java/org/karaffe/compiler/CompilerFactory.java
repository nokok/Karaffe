package org.karaffe.compiler;

public final class CompilerFactory {
    public static KaraffeCompiler createCompiler(final String[] args) {
        return new KaraffeCompiler(args);
    }
}
