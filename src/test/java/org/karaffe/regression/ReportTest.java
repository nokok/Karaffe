package org.karaffe.regression;

import org.karaffe.compiler.phase.util.ShowReportsPhase;
import org.karaffe.compiler.util.CompilerContext;
import org.karaffe.compiler.util.Position;
import org.karaffe.compiler.util.report.Report;
import org.karaffe.compiler.util.report.ReportCode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReportTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void position() {
        CompilerContext ctx = CompilerContext.createInitialContext();
        ctx.add(Report.newReport(ReportCode.ERR_FRONTEND_SYNTAX).with(new Position(1, 4, "Hoge.krf")).build());
        ShowReportsPhase p = new ShowReportsPhase();

        p.execute(ctx);

        assertEquals("ERROR | Syntax Error at 1:4:Hoge.krf" + System.lineSeparator(), outContent.toString());
    }
}
