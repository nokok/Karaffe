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
    /**
     * seffect修飾子付き関数宣言
     */
    S_FUNCTION_DECLARATION,
    /**
     * 変数宣言
     */
    VARIABLE_DECLARATION,
    /**
     * seffect修飾子付き変数宣言
     */
    S_VARIABLE_DECLARATION,
}
