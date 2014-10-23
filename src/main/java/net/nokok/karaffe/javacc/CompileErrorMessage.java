package net.nokok.karaffe.javacc;

public enum CompileErrorMessage {

    TYPE_NOT_FOUND("型が見つかりません"),
    TYPENAME_MUST_BE_START_WITH_UPPER_CASE("型名は大文字から始める必要があります"),
    TYPE_ALIAS_TYPE_PARAMETER("Type Aliasで型パラメータは使用できません"),;

    private final String message;

    private CompileErrorMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
