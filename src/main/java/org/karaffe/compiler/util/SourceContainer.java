package org.karaffe.compiler.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class SourceContainer {
    private final Set<File> compileTargetFiles;

    public SourceContainer() {
        this(new HashSet<>(0));
    }

    public SourceContainer(final Set<File> compileTargetFiles) {
        this.compileTargetFiles = compileTargetFiles;
    }

    public boolean isEmpty() {
        return this.compileTargetFiles.isEmpty();
    }

    public Stream<File> sourceStream() {
        return new ArrayList<>(this.compileTargetFiles).stream();
    }

}
