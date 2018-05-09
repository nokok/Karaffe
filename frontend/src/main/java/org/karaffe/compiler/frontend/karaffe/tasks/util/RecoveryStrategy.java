package org.karaffe.compiler.frontend.karaffe.tasks.util;

public enum RecoveryStrategy {
    /**
     * 失敗したタスクがある場合は即時中止します
     */
    FAIL_FAST,

    /**
     * 失敗したタスクを正常に成功したものとして扱います。
     */
    NO_OPERATION
}
