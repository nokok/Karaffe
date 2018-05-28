package org.karaffe.compiler.base.task;

import org.karaffe.compiler.base.CompilerContext;

import java.util.Collections;
import java.util.Set;

public interface Task {

    String name();

    String description();

    TaskResult run(CompilerContext context);

    default boolean isRunnable(CompilerContext context) {
        return true;
    }

    default boolean isRequired(CompilerContext context) {
        return true;
    }

    default boolean isRepetable(CompilerContext context) {
        return false;
    }

    default boolean isFinally(CompilerContext context) {
        return false;
    }

    default Set<Task> getSubTask(CompilerContext context) {
        return Collections.emptySet();
    }

    /**
     * CompilerContextに対して変更を加えた場合はtrueを返します。
     *
     * @return
     */
    boolean changed();

}
