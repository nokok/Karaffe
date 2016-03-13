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

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(type.toString()).append(System.lineSeparator());
        stringBuilder.append(message).append(System.lineSeparator());
        return stringBuilder.toString();
    }

    public static enum Type {

        ERROR("エラー:"),
        WARNING("警告:"),
        INFO("情報:"),
        DEBUG("デバッグ:"),;

        private final String type;

        Type(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return type;
        }
    }
}
