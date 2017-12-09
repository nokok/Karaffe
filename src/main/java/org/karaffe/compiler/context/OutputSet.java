package org.karaffe.compiler.context;

import org.karaffe.compiler.pos.Position;
import org.karaffe.compiler.util.Report;
import org.karaffe.compiler.util.Report.AdditionalInfo;

public interface OutputSet {
    public <T> void defNewClass(T def);

    public default void addErrorReport(String title, Position position, String message, AdditionalInfo... infomations) {
        this.addReport(Report.createError(title, position, message, infomations));
    }

    public void addReport(Report report);

}
