package org.karaffe.compiler.util;

import org.karaffe.compiler.args.ArgsParser;
import org.karaffe.compiler.args.Flag;
import org.karaffe.compiler.args.Options;
import org.karaffe.compiler.report.Report;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CompilerContext {
    private String[] rawArgs = new String[0];
    private Options options = new Options();
    private List<KaraffeSource> sources = new ArrayList<>();
    private List<Report> reports = new ArrayList<>();
    private Map<Path, byte[]> outputFiles = new HashMap<>();
    private boolean hasError = false;

    public void parseRawArgs(String[] rawArgs) {
        this.rawArgs = Objects.requireNonNull(rawArgs);
        ArgsParser argsParser = new ArgsParser();
        argsParser.parse(rawArgs).ifPresent(options -> {
            this.options = options;
            this.sources.clear();
            this.options.sourceStream().forEach(sources::add);
        });
        this.reports.addAll(argsParser.getReports());
        if (this.reports.stream().anyMatch(Report::isError)) {
            this.hasError = true;
        }
    }

    public boolean hasError() {
        return this.hasError;
    }

    public void add(KaraffeSource source) {
        this.sources.add(Objects.requireNonNull(source));
    }

    public List<KaraffeSource> getSources() {
        return this.sources;
    }

    public void add(BytecodeEntry entry) {
        Objects.requireNonNull(entry);
        this.outputFiles.put(entry.getPath(), entry.getByteCode());
    }

    public void add(Report report) {
        this.reports.add(Objects.requireNonNull(report));
    }

    public Map<Path, byte[]> getOutputFiles() {
        return outputFiles;
    }

    public boolean requireShowUsage() {
        return (this.rawArgs.length == 0 && this.getSources().isEmpty()) || this.hasError;
    }

    public boolean hasFlag(Flag flagName) {
        return this.options.hasFlag(flagName);
    }

    public String getOutputText() {
        List<String> lines = new ArrayList<>();
        for (Report report : this.reports) {
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
        }
        return String.join("\n", lines);
    }
}
