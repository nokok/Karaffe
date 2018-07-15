package org.karaffe.compiler.base.util;

import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.base.report.Report;

public class Errors {
    public static Report reportNoKaraffeFileFound(String absoluteFilePath) {
        return Report.createError("Source file " + absoluteFilePath + " could not be found.", Position.noPos(), "");
    }

    public static Report symbolNotFound(Position position, String symbolName) {
        return Report.createError("Symbol not found : " + symbolName + " at " + position, position, "");
    }

    public static Report syntaxError(Position position, String msg) {
        return Report.createError("Syntax Error", position, msg);
    }

    public static Report unexpectedDirectory(String f) {
        return Report.createError(f + " is directory", Position.noPos(), "");
    }
}
