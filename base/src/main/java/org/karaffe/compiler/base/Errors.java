package org.karaffe.compiler.base;

import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.base.util.Platform;

public class Errors {
    public static void reportNoKaraffeFileFound(String absoluteFilePath) {
        reportError("Source file " + absoluteFilePath + " could not be found.");
    }

    public static void symbolNotFound(Position position, String symbolName) {
        reportError("Symbol not found : " + symbolName + " at " + position);
    }

    public static void syntaxError(Position position, String msg) {
        reportError("Syntax Error at " + position);
        reportError(msg);
    }

    private static void reportError(String msg) {
        Platform.stdErr("Error : " + msg);
    }

    public static void unexpectedDirectory(String f) {
        reportError(f + " is directory");
    }
}
