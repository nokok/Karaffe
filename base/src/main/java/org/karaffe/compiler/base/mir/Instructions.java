package org.karaffe.compiler.base.mir;

import org.karaffe.compiler.base.mir.util.Label;

import java.util.List;
import java.util.function.BiConsumer;

public interface Instructions extends List<Instruction> {
    void updateInternalCache();

    void forEachClasses(BiConsumer<Label, Instructions> action);

    void forEachMethods(BiConsumer<Label, Instructions> action);

}