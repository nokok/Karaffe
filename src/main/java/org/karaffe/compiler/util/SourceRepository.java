package org.karaffe.compiler.util;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Stream;

public class SourceRepository {
    private final Set<File> compileTargetFiles = new LinkedHashSet<>();
    private boolean hasDuplicatedSource = false;

    public void add(final File source) {
        if (!this.compileTargetFiles.add(source)) {
            this.hasDuplicatedSource = true;
        }
    }

    public Stream<File> sourceStream() {
        return new ArrayList<>(this.compileTargetFiles).stream();
    }

    public boolean hasDuplicatedSource() {
        return this.hasDuplicatedSource;
    }

}
