package org.karaffe.compiler.phases;

import java.io.File;
import java.util.Optional;

import org.karaffe.compiler.CompilerContext;
import org.karaffe.compiler.ConfigKeys;

public class SetupPhase extends AbstractTransformer<String[], CompilerContext> {

    public SetupPhase() {
        super(String[].class, CompilerContext.class);
    }

    @Override
    public Optional<CompilerContext> transform(final String[] args) {
        final CompilerContext context = new CompilerContext();
        for (final String arg : args) {
            if (arg.startsWith("-")) {
                final String cmd = arg.substring(1);
                ConfigKeys.valueOf(cmd).ifPresent(context::add);
            }
            if (arg.endsWith(".krf")) {
                context.add(new File(arg));
            }
        }
        return Optional.of(context);
    }

}
