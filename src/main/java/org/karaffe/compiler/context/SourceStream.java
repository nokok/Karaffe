package org.karaffe.compiler.context;

import java.io.File;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.karaffe.compiler.util.SourceCodeContainer;

public interface SourceStream {

    Stream<File> sourceStream();

    boolean hasSource();

    public static SourceStream createSourceStream(String[] args) {
        Set<File> sourceSet = Stream.of(args).filter(arg -> arg.endsWith(".krf")).map(File::new).collect(Collectors.toSet());
        SourceCodeContainer container = new SourceCodeContainer(sourceSet);
        return container;
    }
}