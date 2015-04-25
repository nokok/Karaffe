/**
 * Karaffe Programming Language
 */
package karaffe.compiler.output;

public class Message {

    private final String title;
    private final String message;

    public Message(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public String title() {
        return title;
    }

    public String get() {
        return message;
    }

}
