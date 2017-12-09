package org.karaffe.compiler;

import java.io.File;
import java.util.stream.Stream;

public interface SourceStream {

    Stream<File> sourceStream();

    boolean hasSource();

}