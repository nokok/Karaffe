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
     * 関数宣言
     */
    FUNCTION_DECLARATION,
    /**
     * 変数宣言
     */
    VARIABLE_DECLARATION,
    /**
     * Voidを返す関数の呼び出し
     */
    FUNCTION_CALL,
}
