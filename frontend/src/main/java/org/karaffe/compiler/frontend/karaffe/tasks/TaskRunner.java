package org.karaffe.compiler.frontend.karaffe.tasks;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.frontend.karaffe.tasks.util.RecoveryStrategy;
import org.karaffe.compiler.frontend.karaffe.tasks.util.RunnerResult;
import org.karaffe.compiler.frontend.karaffe.tasks.util.RuntimeTaskException;

import java.util.function.Supplier;

public interface TaskRunner {

    /**
     * タスクを待機状態で追加します
     *
     * @param task 追加するタスク
     */
    void standby(Task task);

    default void standBy(Supplier<Task> sup) {
        standby(sup.get());
    }

    default void standBy(Supplier<Task> sup, Runnable onFailure, Runnable onSuccess) {
        Task task = sup.get();
        task.addOnFailureListener(onFailure);
        task.addOnSuccessListener(onSuccess);
        standby(task);
    }

    /**
     * タスクを実行可能状態で追加し、速やかに実行を開始します。
     *
     * @param task 実行するタスク
     */
    RunnerResult exec(Task task);

    default RunnerResult exec(Supplier<Task> sup) {
        return exec(sup.get());
    }

    /**
     * 待機状態のタスクをすべて実行します。
     * 実行できないタスクについては再度待機状態タスクとしてマークされ、すべてのタスクが終わってから再度実行されます。
     * タスクの実行中は実行時例外 {@link RuntimeTaskException} がスローされる場合があります。
     */
    RunnerResult runAll();

    /**
     * デフォルトのストラテジを用いてタスクランナーを生成します。
     *
     * @param context コンパイラコンテキスト
     * @return タスクランナー
     */
    public static TaskRunner newDefaultTaskRunner(CompilerContext context) {
        return newTaskRunner(context, RecoveryStrategy.FAIL_FAST);
    }

    /**
     * 指定されたストラテジを用いてタスクランナーを生成します。
     *
     * @param context  コンパイラコンテキスト
     * @param strategy リカバリーストラテジ
     * @return タスクランナー
     */
    public static TaskRunner newTaskRunner(CompilerContext context, RecoveryStrategy strategy) {
        if (strategy == RecoveryStrategy.FAIL_FAST) {
            return new DefaultTaskRunner(context);
        }
        throw new UnsupportedOperationException("未実装です");
    }
}
