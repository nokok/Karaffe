package org.karaffe.compiler.frontend.karaffe.tasks;

import org.karaffe.compiler.base.CompilerContext;

import java.util.function.Supplier;

public interface TaskRunner {

    /**
     * タスクを待機状態で追加します
     *
     * @param task
     */
    void standby(Task task);

    default void standBy(Supplier<Task> sup) {
        standby(sup.get());
    }

    /**
     * タスクを実行可能状態で追加します
     *
     * @param task
     */
    void exec(Task task);

    default void exec(Supplier<Task> sup) {
        exec(sup.get());
    }

    void runAll();

    public static TaskRunner defaultTaskRunner(CompilerContext context) {
        return new DefaultTaskRunner(context);
    }
}
