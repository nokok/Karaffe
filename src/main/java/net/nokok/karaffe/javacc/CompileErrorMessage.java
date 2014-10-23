package net.nokok.karaffe.javacc;

public enum CompileErrorMessage {

    TYPENAME_MUST_BE_START_WITH_UPPER_CASE("型名は大文字から始める必要があります"),;
    TYPE_NOT_FOUND("型が見つかりません"),

    private final String message;

    private CompileErrorMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
