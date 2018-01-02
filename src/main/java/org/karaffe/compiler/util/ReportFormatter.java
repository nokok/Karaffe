package org.karaffe.compiler.util;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.karaffe.compiler.pos.Position;
import org.karaffe.compiler.util.Report.AdditionalInfo;
import org.karaffe.compiler.util.Report.StringCodePart;

public class ReportFormatter {

    public String format(List<Report> reports) {
        int maxLineNumber = computeMargin(reports);
        StringBuilder stringBuilder = new StringBuilder();
        int count = 1;
        int maxHeader = 0;
        String blankHeader = repeat(maxLineNumber, " ");
        for (Report report : reports) {
            String headerString = makeHeader(count, maxLineNumber, report);
            if (maxHeader < headerString.length()) {
                maxHeader = headerString.length();
            }
            stringBuilder.append(headerString).append(System.lineSeparator());
            report.codePart().ifPresent(codePart -> {
                stringBuilder.append(makeCodePart(maxLineNumber, codePart, report.position()));
            });
            stringBuilder.append(blankHeader).append("| ").append(report.message()).append(System.lineSeparator());
            for (AdditionalInfo info : report.informations()) {
                for (String message : info.message()) {
                    stringBuilder.append(blankHeader).append("| ").append(message).append(System.lineSeparator());
                }
            }
            count++;
        }
        stringBuilder.append(blankHeader).append("|").append(System.lineSeparator());
        stringBuilder.append("---").append(System.lineSeparator());
        return stringBuilder.toString();
    }

    @SuppressWarnings("boxing")
    private int computeMargin(List<Report> reports) {
        return reports
                .stream()
                .map(Report::codePart)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(c -> c.message().size())
                .max(Comparator.naturalOrder()).orElse(2) + 1;
    }

    private String makeHeader(int issueNumber, int maxLineNumber, Report report) {
        String headerPrefixBar = repeat(maxLineNumber, "-");
        String headerFormat = headerPrefixBar + "+ #%" + String.valueOf(maxLineNumber).length() + "d [%5s] %s in %s";
        return String.format(headerFormat, issueNumber, report.getType().name(), report.title(), report.position());
    }

    private String makeCodePart(int maxLineNumber, StringCodePart part, Position position) {
        StringBuilder builder = new StringBuilder();
        String message = part.message().get(0);

        String f = position.getLineF().map(lineNumber -> String.format("%-" + maxLineNumber + "d", lineNumber)).orElse(repeat(maxLineNumber, "?"));
        builder.append(f).append("| ").append(message).append(System.lineSeparator());
        builder.append(repeat(f.length(), " ")).append("| ");
        position.getColumnF().ifPresent(column -> {
            for (int i = 1; i < column; i++) {
                builder.append(" ");
            }
            builder.append("^").append(System.lineSeparator());
        });

        return builder.toString();
    }

    public static String repeat(int count, String c) {
        return String.format("%0" + count + "d", 0).replaceAll("0", c);
    }
}
