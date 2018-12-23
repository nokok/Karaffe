package org.karaffe.compiler;

public final class Platform {
    public static boolean isWindows() {
        return System.lineSeparator().equals("\r\n");
    }
}
