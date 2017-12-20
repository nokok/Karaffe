package org.karaffe.compiler;

public final class CompilerFactory {
    public static KaraffeCompiler createCompiler(String[] args) {
        return new KaraffeCompiler(args);
    }
}
