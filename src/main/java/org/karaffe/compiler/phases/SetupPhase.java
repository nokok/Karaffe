package org.karaffe.compiler.phases;

import java.util.Optional;

import org.karaffe.compiler.context.CompilerConfig;
import org.karaffe.compiler.util.ProgressTraceable;
import org.karaffe.compiler.util.Traceable;

public class SetupPhase extends AbstractTransformer<String[], CompilerConfig> implements Traceable, ProgressTraceable {

    public SetupPhase() {
        super(String[].class, CompilerConfig.class);
    }

    @Override
    public Optional<CompilerConfig> transform(final String[] args) {
        final CompilerConfig context = CompilerConfig.defaultConfig();
        return Optional.of(context);
    }

}
