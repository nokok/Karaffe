package org.karaffe.compiler.base.util;

import java.io.InputStream;
import java.io.PrintStream;

public enum Platform {

    INSTANCE,
    ;

    public static String ANSI_RESET;
    public static String ANSI_BOLD;
    public static String ANSI_BLACK;
    public static String ANSI_RED;
    public static String ANSI_GREEN;
    public static String ANSI_YELLOW;
    public static String ANSI_BLUE;
    public static String ANSI_PURPLE;
    public static String ANSI_CYAN;
    public static String ANSI_WHITE;
    private static PrintStream defaultStdOut = System.out;
    private static PrintStream stdOut = defaultStdOut;
    private static PrintStream defaultStdErr = System.err;
    private static PrintStream stdErr = defaultStdErr;
    private static InputStream defaultStdIn = System.in;
    private static InputStream stdIn = defaultStdIn;

    static {
        init();
    }

    public static void init() {
        String colorVar = System.getProperty("karaffe.console.no_color");
        if (colorVar == null) {
            colorVar = System.getenv("NO_COLOR");
        }
        if (Platform.isWindows()) {
            colorVar = "NO_COLOR";
        }
        if (colorVar == null) {
            ANSI_RESET = "\u001B[0m";
            ANSI_BOLD = "\u001B[1m";
            ANSI_BLACK = "\u001B[30m";
            ANSI_RED = "\u001B[31m";
            ANSI_GREEN = "\u001B[32m";
            ANSI_YELLOW = "\u001B[33m";
            ANSI_BLUE = "\u001B[34m";
            ANSI_PURPLE = "\u001B[35m";
            ANSI_CYAN = "\u001B[36m";
            ANSI_WHITE = "\u001B[37m";
        } else {
            ANSI_RESET = "";
            ANSI_BOLD = "";
            ANSI_BLACK = "";
            ANSI_RED = "";
            ANSI_GREEN = "";
            ANSI_YELLOW = "";
            ANSI_BLUE = "";
            ANSI_PURPLE = "";
            ANSI_CYAN = "";
            ANSI_WHITE = "";
        }
    }

    public static boolean isWindows() {
        return "\\".equals(System.getProperty("file.separator"));
    }

    @Deprecated
    public static PrintStream getStdOut() {
        return Platform.stdOut;
    }

    @Deprecated
    public static void setStdOut(PrintStream stdOut) {
        Platform.stdOut = stdOut;
    }

    @Deprecated
    public static PrintStream getStdErr() {
        return Platform.stdErr;
    }

    @Deprecated
    public static void setStdErr(PrintStream stdErr) {
        Platform.stdErr = stdErr;
    }

    @Deprecated
    public static void setStdIn(InputStream stdIn) {
        Platform.stdIn = stdIn;
    }

    @Deprecated
    public static void stdOut() {
        stdOut.println();
    }

    @Deprecated
    public static void stdOut(Object msg) {
        stdOut.println(msg);
    }

    @Deprecated
    public static void stdErr(Object msg) {
        stdErr.println(msg);
    }

    @Deprecated
    public static void print(Object object) {
        Platform.stdOut.print(object);
    }

    @Deprecated
    public static void println(Object object) {
        Platform.stdOut.println(object);
    }
}
