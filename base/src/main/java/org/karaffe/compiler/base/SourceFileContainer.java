package org.karaffe.compiler.base;

import org.karaffe.compiler.base.util.SourceFile;

import java.util.stream.Stream;

public interface SourceFileContainer {

    void addSourceFile(SourceFile sourceFile);

    Stream<SourceFile> sourceFileStream();

    boolean hasFile();
}
