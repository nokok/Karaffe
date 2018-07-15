package org.karaffe.compiler.base;

import org.karaffe.compiler.base.report.Report;

import java.util.List;

public interface ReportContainer {
    void addReport(Report report);

    List<Report> getReports();

    default boolean hasErrorReport() {
        return this.getReports()
                .stream()
                .map(Report::getType)
                .anyMatch(a -> a == Report.ReportType.ERROR);
    }

    default boolean hasAnyReport() {
        return !this.getReports().isEmpty();
    }
}
