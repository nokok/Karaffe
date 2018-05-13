package org.karaffe.compiler.base.task;

import org.karaffe.compiler.base.CompilerContext;

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

    /**
     * CompilerContextに対して変更を加えた場合はtrueを返します。
     *
     * @return
     */
    boolean changed();

}
