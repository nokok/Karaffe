package net.nokok.karaffe.javacc.ast;

public class LiteralTestUtil {

    public static Program createProgramFromValue(String value) throws ParseException {
        Program program = new KaraffeParser("x = " + value + "\n").parse();
        return program;
    }

    public static VariableDeclaration getAndCastVariableDeclaration(Program program) {
        return (VariableDeclaration) program.getNodes().get(0);
    }

    public static <T> boolean tryValue(String value, Class<T> clazz) throws ParseException {
        VariableDeclaration d = getAndCastVariableDeclaration(createProgramFromValue(value));
        return d.getNode().getClass().getTypeName().equals(clazz.getTypeName());
    }
}
