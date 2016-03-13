package karaffe.compiler;

public class TypeCheckException extends RuntimeException {

    public TypeCheckException() {

    }

    public TypeCheckException(Position pos) {
        super("Type Error at " + pos);
    }
}
