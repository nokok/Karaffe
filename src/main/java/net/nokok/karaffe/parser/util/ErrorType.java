/**
 *
 * Karaffe Programming Language
 *   __ _____   ___  ___   ____________
 *   / //_/ _ | / _ \/ _ | / __/ __/ __/
 *  / , \/ __ |/ , _/ __ |/ _// _// _/
 * /_/|_/_/ |_/_/|_/_/ |_/_/ /_/ /___/
 *
 */
package net.nokok.karaffe.parser.util;

public enum ErrorType {

    IDENTIFIER_NOT_FOUND("id_not_found"),
    TYPE_MISMATCH("type_mismatch"),
    SYNTAX_ERROR("syntax_error"),;

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
