package org.karaffe.compiler;

import org.karaffe.compiler.util.ProgressTraceable;
import org.karaffe.compiler.util.Traceable;

public class Main implements Traceable, ProgressTraceable {

    public static void main(final String[] args) throws Exception {
        KaraffeCompiler compiler = new KaraffeCompiler(args);
        System.exit(compiler.call().intValue());
    }

    public static String usage() {
        return "Usage not available";
    }

}