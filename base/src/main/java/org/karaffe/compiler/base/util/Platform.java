package org.karaffe.compiler.base.util;

import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Objects;

public enum Platform {

    INSTANCE,;

    static {
        String noColorVar = System.getenv("NO_COLOR");
        if (noColorVar != null) {
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
        } else {
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
        }
    }

    public static final String ANSI_RESET;
    public static final String ANSI_BOLD;
    public static final String ANSI_BLACK;
    public static final String ANSI_RED;
    public static final String ANSI_GREEN;
    public static final String ANSI_YELLOW;
    public static final String ANSI_BLUE;
    public static final String ANSI_PURPLE;
    public static final String ANSI_CYAN;
    public static final String ANSI_WHITE;
    private static PrintStream defaultStdOut = System.out;
    private static PrintStream stdOut = defaultStdOut;
    private static PrintStream defaultStdErr = System.err;
    private static PrintStream stdErr = defaultStdErr;
    private static InputStream defaultStdIn = System.in;
    private static InputStream stdIn = defaultStdIn;

    public static boolean isWindows() {
        return File.separatorChar == '\\';
    }

    public static PrintStream getStdOut() {
        return Platform.stdOut;
    }

    public static void setStdOut(PrintStream stdOut) {
        Platform.stdOut = stdOut;
    }

    public static PrintStream getStdErr() {
        return Platform.stdErr;
    }

    public static void setStdErr(PrintStream stdErr) {
        Platform.stdErr = stdErr;
    }

    public static void setStdIn(InputStream stdIn) {
        Platform.stdIn = stdIn;
    }

    public static void stdOut() {
        stdOut.println();
    }

    public static void stdOut(Object msg) {
        stdOut.println(msg);
    }

    public static void stdErr(Object msg) {
        stdErr.println(msg);
    }

    public static String mkGreen(Object msg) {
        return ANSI_GREEN + Objects.toString(msg) + ANSI_RESET;
    }

    public static String mkYellow(Object msg) {
        return ANSI_YELLOW + Objects.requireNonNull(msg) + ANSI_RESET;
    }
}
