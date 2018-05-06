package org.karaffe.compiler.base;

import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.base.util.Platform;

public class Errors {
    public static void reportNoKaraffeFileFound(String absoluteFilePath) {
        Platform.stdErr("Source file " + absoluteFilePath + " could not be found.");
    }

    public static void symbolNotFound(String symbolName, Position position) {
        Platform.stdErr("Symbol not found : " + symbolName + " at " + position);
    }

    public static void syntaxError(Position position, String msg) {
        Platform.stdErr("Syntax Error at : " + position);
        Platform.stdErr(msg);
    }
}
