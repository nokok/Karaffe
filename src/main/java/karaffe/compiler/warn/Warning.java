/**
 * Karaffe Programming Language
 */
package karaffe.compiler.warn;

import java.util.Optional;

public class Warning {

    private final String title;
    private final String message;
    private final Optional<String> code;

    public Warning(String title, String message) {
        this(title, message, null);
    }

    public Warning(String title, String message, String code) {
        this.title = title;
        this.message = message;
        this.code = Optional.ofNullable(code);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("警告: ").append(title).append(System.lineSeparator());
        sb.append("  ").append(message);
        code.ifPresent(sb::append);
        return sb.toString();
    }

}
