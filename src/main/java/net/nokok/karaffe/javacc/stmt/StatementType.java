package net.nokok.karaffe.javacc.stmt;

public enum StatementType {

    /**
     * 改行ステートメント
     */
    NEWLINE,
    /**
     * 改行が2つ存在する、スコープ区切りのステートメント
     */
    SCOPE_SPLITTER,
    /**
     * 型宣言ステートメント
     */
    STRUCT,
    /**
     * Type Aliasステートメント
     */
    TYPE_ALIAS,
}
