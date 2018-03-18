package org.karaffe.compiler.context;

import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.util.Report;
import org.karaffe.compiler.util.Report.AdditionalInfo;

public interface OutputSet {
    public default void addErrorReport(final String title, final Position position, final String message, final AdditionalInfo... infomations) {
        this.addReport(Report.createError(title, position, message, infomations));
    }

    public void addReport(Report report);

}
