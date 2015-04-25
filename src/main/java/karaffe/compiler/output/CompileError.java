/**
 * Karaffe Programming Language
 */
package karaffe.compiler.output;

public class CompileError {

    private final Message msg;

    public CompileError(String title, String message) {
        this.msg = new Message(title, message);
    }

    @Override
    public String toString() {
        return String.format("エラー: %s\n"
                + "  %s", msg.title(), msg.get());
    }
}
