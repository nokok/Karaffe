package net.nokok.karaffe.javacc;

public enum CompileErrorMessage {

    TYPE_NOT_FOUND("次の型が見つかりません "),;

    private final String message;

    private CompileErrorMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
