package org.karaffe.compiler.base.util;

import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;

public enum Platform {

    INSTANCE,;

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

    public static void setStdIn(InputStream stdIn) {
        Platform.stdIn = stdIn;
    }

    public static void setStdOut(PrintStream stdOut) {
        Platform.stdOut = stdOut;
    }

    public static void setStdErr(PrintStream stdErr) {
        Platform.stdErr = stdErr;
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
}
