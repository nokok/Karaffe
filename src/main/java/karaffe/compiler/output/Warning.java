/**
 * Karaffe Programming Language
 */
package karaffe.compiler.output;

public class Warning {

    private final Message msg;

    public Warning(String title, String message) {
        this.msg = new Message(title, message);
    }

    @Override
    public String toString() {
        return String.format("警告: %s\n"
                + "   %s", msg.title(), msg.get());
    }

}
