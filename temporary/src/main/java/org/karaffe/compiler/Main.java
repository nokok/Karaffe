package org.karaffe.compiler;

import org.karaffe.compiler.context.CompilerConfig;
import org.karaffe.compiler.util.ProgressTraceable;
import org.karaffe.compiler.util.Traceable;

public class Main implements Traceable, ProgressTraceable {

    public static void main(final String[] args) throws Exception {
        CompilerConfig config = CompilerConfig.buildConfig(args);
        final KaraffeCompiler compiler = new KaraffeCompiler(config);
        System.exit(compiler.call().intValue());
    }

}