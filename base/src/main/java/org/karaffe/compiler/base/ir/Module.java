package org.karaffe.compiler.base.ir;

import org.karaffe.compiler.base.ir.util.Binding;

import java.util.List;
import java.util.stream.Stream;

public interface Module extends Element {
    String getModuleName();

    void add(Function function);

    void add(Binding variable);

    void addFirst(Function function);

    List<Function> getFunctions();

    List<Binding> getModuleVariables();

    default Stream<Function> functionStream() {
        return this.getFunctions().stream();
    }

}
