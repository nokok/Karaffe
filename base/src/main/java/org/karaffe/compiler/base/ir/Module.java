package org.karaffe.compiler.base.ir;

import java.util.List;
import java.util.stream.Stream;

public interface Module extends Element {
    String getModuleName();

    void add(Function function);

    void addFirst(Function function);

    List<Function> getFunctions();

    default Stream<Function> functionStream() {
        return this.getFunctions().stream();
    }

}
