package org.karaffe.compiler.phases;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.karaffe.compiler.CompilerContext;
import org.karaffe.compiler.util.ProgressTraceable;
import org.karaffe.compiler.util.Traceable;

public class SetupPhase extends AbstractTransformer<String[], CompilerContext> implements Traceable, ProgressTraceable {

    public SetupPhase() {
        super(String[].class, CompilerContext.class);
    }

    @Override
    public Optional<CompilerContext> transform(final String[] args) {
        this.progress("Building Task...");
        final List<File> sourceFiles = Stream.of(args).filter(arg -> arg.endsWith(".krf")).map(File::new).collect(Collectors.toList());

        final Set<File> sourceSet = new HashSet<>(sourceFiles);

        if (sourceFiles.size() != sourceSet.size()) {
            // Duplication Detected
            this.error("source duplicated.");
        }

        final CompilerContext context = CompilerContext.defaultContext(sourceSet);
        this.info("Configuration Loaded.");
        return Optional.of(context);
    }

}
