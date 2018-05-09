package org.karaffe.compiler.frontend.karaffe.tasks;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.frontend.karaffe.tasks.util.TaskResult;

import java.util.List;

public interface Task {

    String name();

    String description();

    TaskResult run(CompilerContext context);

    default boolean isRunnable(CompilerContext context) {
        return true;
    }

    /**
     * CompilerContextに対して変更を加えた場合はtrueを返します。
     *
     * @return
     */
    boolean changed();

    List<Runnable> getOnSuccessListeners();

    List<Runnable> getOnFailureListeners();

    void addOnSuccessListener(Runnable runnable);

    void addOnFailureListener(Runnable runnable);

}
