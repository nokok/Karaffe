package org.karaffe.compiler.base.task;

public interface NoDescriptionTask extends Task {
    @Override
    default String description() {
        return "(N/A)";
    }
}
