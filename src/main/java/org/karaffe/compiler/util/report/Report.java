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
    return new Builder(reportCode);
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
    private final ReportCode reportCode;
    private String header;
    private Position position = null;
    private String body = null;

    private Builder(ReportCode reportCode) {
      this.reportCode = Objects.requireNonNull(reportCode);
      this.header = reportCode.toReportHeader();
    }

    public Builder with(Position position) {
      this.position = Objects.requireNonNull(position);
      return this;
    }

    public Report build() {
      if (this.reportCode.isRequireBody() && (this.body == null || this.body.isBlank())) {
        throw new IllegalStateException("body required");
      }
      if (this.reportCode.isRequirePosition() && (this.position == null || this.position.isNoPos())) {
        throw new IllegalStateException("position required");
      }
      if (this.header == null) {
        throw new IllegalStateException("header required");
      }
      if (this.header.contains("{0}")) {
        throw new IllegalStateException("illegal variables : " + this.header);
      }
      Report report = new Report();
      report.reportType = reportCode.toReportType();
      report.header = this.header;
      report.position = this.position;
      report.body = this.body;
      return report;
    }

    public Builder withBody(String body) {
      this.body = Objects.requireNonNull(body);
      return this;
    }

    public Builder withVariable(Object obj, Object... vars) {
      this.header = MessageFormat.format(this.header, obj, vars);
      return this;
    }
  }
}
