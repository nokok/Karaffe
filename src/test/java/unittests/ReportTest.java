package unittests;

import org.junit.Test;
import org.karaffe.compiler.pos.Position;
import org.karaffe.compiler.util.Report;

public class ReportTest {

    @Test
    public void testReportString() {
        Report report = Report.createError("Title", Position.noPos(), "Message");
    }
}
