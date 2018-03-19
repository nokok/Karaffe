package it;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.base.util.Report;
import org.karaffe.compiler.base.util.Report.FoundAndRequired;
import org.karaffe.compiler.base.util.Report.StringCodePart;
import org.karaffe.compiler.base.util.ReportFormatter;

public class ReportFormatterTest {

    @Test
    public void testEmptyReport() {
        final ReportFormatter formatter = new ReportFormatter();
        final String msg = formatter.format(new ArrayList<>());
        assertEquals("", msg);
    }

    @Test
    public void testReportString() {
        final StringCodePart part = new StringCodePart("  println(1)");
        final FoundAndRequired foundAndRequired = new FoundAndRequired("String", "Int");
        final Report report = Report.createError("Title", Position.of("source.krf", 1, 11), "Message", part, foundAndRequired);
        final ReportFormatter formatter = new ReportFormatter();
        final StringBuilder expected = new StringBuilder();
        expected.append("--+ #1 [ERROR] Title in 1:11 at source.krf").append(System.lineSeparator());
        expected.append("1 |   println(1)").append(System.lineSeparator());
        expected.append("  |           ^").append(System.lineSeparator());
        expected.append("  | Message").append(System.lineSeparator());
        expected.append("  | found    : ").append("String").append(System.lineSeparator());
        expected.append("  | required : ").append("Int").append(System.lineSeparator());
        expected.append("  |").append(System.lineSeparator());
        expected.append("---").append(System.lineSeparator());
        final String actual = formatter.format(new ArrayList<>(Arrays.asList(report)));
        assertEquals(expected.toString(), actual);
    }
}
