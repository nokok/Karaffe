package net.nokok.karaffe.javacc.stmt;

public enum StatementType {

    /**
     * 空行
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
    /**
     * seffect修飾子付きType Aliasステートメント
     */
    S_TYPE_ALIAS,
    /**
     * 関数宣言
     */
    FUNCTION_DECLARATION,
}
