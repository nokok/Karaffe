package org.karaffe.compiler.base.task;

public interface ReadOnlyTask extends Task {
    @Override
    default boolean changed() {
        return false;
    }
}
