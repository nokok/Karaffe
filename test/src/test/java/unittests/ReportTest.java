package unittests;

import org.junit.Test;
import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.util.Report;

public class ReportTest {

    @Test
    public void testReportString() {
        final Report report = Report.createError("Title", Position.noPos(), "Message");
    }
}
