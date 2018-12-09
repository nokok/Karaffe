package org.karaffe.compiler.report;

import org.karaffe.compiler.util.Position;

import java.util.Objects;

public class Report {

    private ReportType reportType;
    private Position position;
    private String header;
    private String body;

    public ReportType getReportType() {
        return reportType;
    }

    public Position getPosition() {
        return position;
    }

    public String getHeader() {
        return header;
    }

    public String getBody() {
        return body;
    }

    public static Report.Builder newErrorReport(String title) {
        return new Builder(title, ReportType.ERROR);
    }

    public static Report.Builder newWarningReport(String title) {
        return new Builder(title, ReportType.WARN);
    }

    public static Report.Builder newInfoReport(String title) {
        return new Builder(title, ReportType.INFO);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s at %s", this.reportType.name(), this.header, this.position);
    }

    public static class Builder {
        private final String title;
        private final ReportType reportType;
        private Position position = null;
        private String body;

        private Builder(String title, ReportType reportType) {
            this.title = Objects.requireNonNull(title);
            this.reportType = Objects.requireNonNull(reportType);
            if (this.title.trim().isEmpty()) {
                throw new IllegalArgumentException("empty title");
            }
        }

        public Builder with(Position position) {
            this.position = Objects.requireNonNull(position);
            return this;
        }

        public Report build() {
            Report report = new Report();
            if (this.reportType == null) {
                throw new IllegalStateException("null reportType");
            }
            report.reportType = this.reportType;
            if (this.title == null) {
                throw new IllegalStateException("null title");
            }
            report.header = this.title;
            if (this.position == null) {
                report.position = new Position(-1, -1, "<unknown>");
            } else {
                report.position = this.position;
            }
            if (this.body != null) {
                report.body = this.body;
            }
            return report;
        }

        public Builder withBody(String body) {
            this.body = Objects.requireNonNull(body);
            return this;
        }
    }
}
