package it;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import org.karaffe.compiler.pos.Position;
import org.karaffe.compiler.util.Report;
import org.karaffe.compiler.util.Report.FoundAndRequired;
import org.karaffe.compiler.util.Report.StringCodePart;
import org.karaffe.compiler.util.ReportFormatter;

public class ReportFormatterTest {

    @Test
    public void testReportString() {
        StringCodePart part = new StringCodePart("  println(1)");
        FoundAndRequired foundAndRequired = new FoundAndRequired("String", "Int");
        Report report = Report.createError("Title", Position.of("source.krf", 1, 11), "Message", part, foundAndRequired);
        ReportFormatter formatter = new ReportFormatter();
        StringBuilder expected = new StringBuilder();
        expected.append("--+ #1 [ERROR] Title in 1:11 at source.krf").append(System.lineSeparator());
        expected.append("1 |   println(1)").append(System.lineSeparator());
        expected.append("  |           ^").append(System.lineSeparator());
        expected.append("  | Message").append(System.lineSeparator());
        expected.append("  | found    : ").append("String").append(System.lineSeparator());
        expected.append("  | required : ").append("Int").append(System.lineSeparator());
        expected.append("  |").append(System.lineSeparator());
        expected.append("---").append(System.lineSeparator());
        String actual = formatter.format(new ArrayList<>(Arrays.asList(report)));
        assertEquals(expected.toString(), actual);
    }

    @Test
    public void testEmptyReport() {
        ReportFormatter formatter = new ReportFormatter();
        String msg = formatter.format(new ArrayList<>());
        assertEquals("", msg);
    }
}
