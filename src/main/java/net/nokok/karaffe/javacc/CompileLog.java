package net.nokok.karaffe.javacc;

public class CompileLog {

    private static boolean DEBUG = true;
    private int nestLevel = 0;

    public static void print(String message) {
        if ( DEBUG ) {
            System.out.println(message);
        }
    }

    public static void error(Token token) {
        System.out.println("Compile Error.");
        System.out.println(token.image);
    }

    public static void setDebug() {
        DEBUG = true;
    }

    public static void unsetDebug() {
        DEBUG = false;
    }
}
