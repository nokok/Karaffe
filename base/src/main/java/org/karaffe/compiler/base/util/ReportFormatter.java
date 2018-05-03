package org.karaffe.compiler.base.util;

import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.base.util.Report.AdditionalInfo;
import org.karaffe.compiler.base.util.Report.StringCodePart;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ReportFormatter {

    public static String repeat(final int count, final String c) {
        return String.format("%0" + count + "d", 0).replaceAll("0", c);
    }

    @SuppressWarnings("boxing")
    private int computeMargin(final List<Report> reports) {
        return reports
                .stream()
                .map(Report::codePart)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(c -> c.message().size())
                .max(Comparator.naturalOrder()).orElse(2) + 1;
    }

    public String format(final List<Report> reports) {
        if (reports.isEmpty()) {
            return "";
        }
        final int maxLineNumber = this.computeMargin(reports);
        final StringBuilder stringBuilder = new StringBuilder();
        int count = 1;
        int maxHeader = 0;
        final String blankHeader = repeat(maxLineNumber, " ");
        for (final Report report : reports) {
            final String headerString = this.makeHeader(count, maxLineNumber, report);
            if (maxHeader < headerString.length()) {
                maxHeader = headerString.length();
            }
            stringBuilder.append(headerString).append(System.lineSeparator());
            report.codePart().ifPresent(codePart -> {
                stringBuilder.append(this.makeCodePart(maxLineNumber, codePart, report.position()));
            });
            stringBuilder.append(blankHeader).append("| ").append(report.message()).append(System.lineSeparator());
            for (final AdditionalInfo info : report.informations()) {
                for (final String message : info.message()) {
                    stringBuilder.append(blankHeader).append("| ").append(message).append(System.lineSeparator());
                }
            }
            count++;
        }
        stringBuilder.append(blankHeader).append("|").append(System.lineSeparator());
        stringBuilder.append("---").append(System.lineSeparator());
        return stringBuilder.toString();
    }

    private String makeCodePart(final int maxLineNumber, final StringCodePart part, final Position position) {
        final StringBuilder builder = new StringBuilder();
        final String source = part.sourceCode();
        final String f = String.format("%-" + maxLineNumber + "s", position.getLine());
        builder.append(f).append("| ").append(source).append(System.lineSeparator());
        builder.append(repeat(f.length(), " ")).append("| ");
        position.getColNumber().ifPresent(column -> {
            for (int i = 1; i < column; i++) {
                builder.append(" ");
            }
            builder.append("^").append(System.lineSeparator());
        });

        return builder.toString();
    }

    private String makeHeader(final int issueNumber, final int maxLineNumber, final Report report) {
        final String headerPrefixBar = repeat(maxLineNumber, "-");
        final String headerFormat = headerPrefixBar + "+ #%" + String.valueOf(maxLineNumber).length() + "d [%5s] %s in %s";
        return String.format(headerFormat, issueNumber, report.getType().name(), report.title(), report.position());
    }
}
