package org.karaffe.compiler.report;

import org.karaffe.compiler.util.CompilerContext;
import org.karaffe.compiler.util.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class ReportFormatter {

    private final CompilerContext context;

    public ReportFormatter() {
        this.context = null;
    }

    public ReportFormatter(CompilerContext context) {
        this.context = context;
    }

    public String format(Report report) {
        List<String> lines = new ArrayList<>();
        StringBuilder reportText = new StringBuilder();
        String reportTypeName = String.format("%-5s", report.getReportType().name());
        reportText.append("[").append(reportTypeName).append("] ").append(report.getHeader());
        Optional<Position> optPos = report.getPosition();
        optPos.ifPresent(position -> reportText.append(" at ").append(position));
        lines.add(reportText.toString());
        reportText.setLength(0);
        report.getBody().ifPresent(body -> lines.add(reportText.append(body).toString()));
        if (context != null) {
            optPos.ifPresent(
                    pos -> context.getSource(pos.getSourceName()).ifPresent(
                            source -> {
                                String line = source.getCodeByLine(pos.getLine());
                                lines.add(line);
                                StringBuilder padding = new StringBuilder();
                                IntStream.range(0, pos.getColumn()).mapToObj(i -> " ").forEach(padding::append);
                                IntStream.range(pos.getColumn(), pos.getEndColumn()).mapToObj(i -> "~").forEach(padding::append);
                                padding.append("^");
                                lines.add(padding.toString());
                            }));
        }
        return String.join("\n", lines);
    }
}
