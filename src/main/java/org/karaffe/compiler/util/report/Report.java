package org.karaffe.compiler.util.report;

import org.karaffe.compiler.util.Position;

import java.text.MessageFormat;
import java.util.Objects;
import java.util.Optional;

public class Report {

  private ReportType reportType;
  private Position position;
  private String header;
  private String body;

  public static Report.Builder newReport(ReportCode reportCode) {
    return new Builder(reportCode.toTitleName(), reportCode.toReportType());
  }

  @Deprecated
  public static Report.Builder newErrorReport(String title) {
    return new Builder(title, ReportType.ERROR);
  }

  @Deprecated
  public static Report.Builder newWarningReport(String title) {
    return new Builder(title, ReportType.WARN);
  }

  @Deprecated
  public static Report.Builder newInfoReport(String title) {
    return new Builder(title, ReportType.INFO);
  }

  public ReportType getReportType() {
    return reportType;
  }

  public Optional<Position> getPosition() {
    return Optional.ofNullable(position);
  }

  public String getHeader() {
    return header;
  }

  public Optional<String> getBody() {
    return Optional.ofNullable(body);
  }

  public boolean isError() {
    return this.reportType.equals(ReportType.ERROR);
  }

  @Override
  public String toString() {
    return String.format("[%s] %s at %s", this.reportType.name(), this.header, this.position);
  }

  public static class Builder {
    private String title;
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
      report.position = this.position;
      report.body = this.body;
      return report;
    }

    public Builder withBody(String body) {
      this.body = Objects.requireNonNull(body);
      return this;
    }

    public Builder withVariable(Object obj, Object... vars) {
      this.title = MessageFormat.format(this.title, obj, vars);
      return this;
    }
  }
}
