package org.karaffe.compiler.context;

import java.io.File;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.karaffe.compiler.util.SourceCodeContainer;

public interface SourceStream {

    public static SourceStream createSourceStream(final String[] args) {
        final Set<File> sourceSet = Stream.of(args).filter(arg -> arg.endsWith(".krf")).map(File::new).collect(Collectors.toSet());
        final SourceCodeContainer container = new SourceCodeContainer(sourceSet);
        return container;
    }

    boolean hasSource();

    Stream<File> sourceStream();
}