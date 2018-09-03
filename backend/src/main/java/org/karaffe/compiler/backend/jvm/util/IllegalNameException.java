package org.karaffe.compiler.backend.jvm.util;

import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.base.report.Report;

public class IllegalNameException extends BackendException {
    public IllegalNameException(String identifier) {
        super("Illegal name : " + identifier);
    }

    @Override
    public Report getReport() {
        return Report.createError("Internal Error", Position.noPos(), "Illegal identifier");
    }
}
