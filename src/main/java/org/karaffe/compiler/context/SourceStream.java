package org.karaffe.compiler.context;

import java.io.File;
import java.util.stream.Stream;

public interface SourceStream {

    Stream<File> sourceStream();

    boolean hasSource();

}