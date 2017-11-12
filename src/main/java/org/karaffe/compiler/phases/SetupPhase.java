package org.karaffe.compiler.phases;

import java.io.File;
import java.util.Optional;

import org.karaffe.compiler.CompilerContext;
import org.karaffe.compiler.ConfigKeys;
import org.karaffe.compiler.ConfigKeys.Config;
import org.karaffe.compiler.util.Traceable;

public class SetupPhase extends AbstractTransformer<String[], CompilerContext> implements Traceable {

    public SetupPhase() {
        super(String[].class, CompilerContext.class);
    }

    @Override
    public Optional<CompilerContext> transform(final String[] args) {
        final CompilerContext context = CompilerContext.defaultContext();
        for (final String arg : args) {
            if (arg.startsWith("--")) {
                final String cmd = arg.substring(2);
                final Optional<Config> configOpt = ConfigKeys.valueOf(cmd);
                continue;
            }
            if (arg.startsWith("-")) {
                final String cmd = arg.substring(1);
                final Optional<Config> configOpt = ConfigKeys.valueOf(cmd);
            }
            if (arg.endsWith(".krf")) {
                context.add(new File(arg));
            }
        }
        return Optional.of(context);
    }

}
