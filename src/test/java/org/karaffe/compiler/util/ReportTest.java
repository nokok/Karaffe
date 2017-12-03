package org.karaffe.compiler.util;

import org.junit.Test;
import org.karaffe.compiler.pos.Position;

public class ReportTest {

    @Test
    public void testReportString() {
        Report report = Report.createError("Title", Position.noPos(), "Message");
    }
}
