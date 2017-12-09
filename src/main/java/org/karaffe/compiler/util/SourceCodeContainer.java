package org.karaffe.compiler.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import org.karaffe.compiler.SourceStream;

public class SourceCodeContainer implements SourceStream {
    private final Set<File> compileTargetFiles;

    public SourceCodeContainer() {
        this(new HashSet<>(0));
    }

    public SourceCodeContainer(final Set<File> compileTargetFiles) {
        this.compileTargetFiles = compileTargetFiles;
    }

    public boolean isEmpty() {
        return this.compileTargetFiles.isEmpty();
    }

    @Override
    public Stream<File> sourceStream() {
        return new ArrayList<>(this.compileTargetFiles).stream();
    }

    @Override
    public boolean hasSource() {
        return !this.isEmpty();
    }

}
