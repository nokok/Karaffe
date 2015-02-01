/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.util;

public enum ErrorType {

    TYPE_MISMATCH("type_mismatch"),
    SYNTAX_ERROR("syntax_error"),
    //Modifiers
    BAD_MODIFIER_NUM("bad_modifier_num"),
    DUP_MODIFIER("dup_modifier"),
    //Not Found
    IDENTIFIER_NOT_FOUND("id_not_found"),
    TYPE_NOT_FOUND("type_not_found"),
    DEFAULT_IMPORTS_FAILED("default_imports_failed");

    private final String errorMessageId;

    private ErrorType(String message) {
        this.errorMessageId = message;
    }

    @Override
    public String toString() {
        //TODO: リソースから言語別のエラーメッセージを持ってくる
        return errorMessageId;
    }
}
