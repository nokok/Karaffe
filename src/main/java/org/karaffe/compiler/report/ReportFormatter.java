package org.karaffe.compiler.report;

import java.util.ArrayList;
import java.util.List;

public class ReportFormatter {

    public String format(Report report) {
        List<String> lines = new ArrayList<>();
        StringBuilder reportText = new StringBuilder();
        String reportTypeName = String.format("%-5s", report.getReportType().name());
        reportText.append("[").append(reportTypeName).append("] ").append(report.getHeader());
        if (report.getPosition().getLine() != -1) {
            reportText.append(" at ").append(report.getPosition());
        }
        lines.add(reportText.toString());
        reportText.setLength(0);
        if (report.getBody() != null) {
            lines.add(reportText.append("[").append(reportTypeName).append("]   ").append(report.getBody()).toString());
        }
        return String.join("\n", lines);
    }
}
