/**
 * Karaffe Programming Language
 */
package karaffe.compiler.phase;

public class ToDo {

    private final Type type;
    private final String message;

    public ToDo(Type type, String message) {
        this.type = type;
        this.message = message;
    }

    public Type type() {
        return type;
    }

    public String message() {
        return message;
    }

    public static enum Type {

        CRITICAL,
        ERROR,
        WARNING,
        INFO,
        DEBUG,;
    }
}
