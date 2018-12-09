package org.karaffe.compiler.util;

import org.karaffe.compiler.report.Report;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Stack;

public class CompilerContext {
    private String[] rawArgs = new String[0];
    private Set<String> flags = new HashSet<>();
    private List<KaraffeSource> sources = new ArrayList<>();
    private List<Report> reports = new ArrayList<>();
    private Map<Path, byte[]> outputFiles = new HashMap<>();
    private boolean hasError = false;

    public void parseRawArgs(String[] rawArgs) {
        this.rawArgs = Objects.requireNonNull(rawArgs);
        Stack<String> argStack = new Stack<>();
        argStack.addAll(Arrays.asList(rawArgs));
        while (!argStack.empty()) {
            String arg = argStack.pop();
            if (arg.endsWith(".krf")) {
                try {
                    this.sources.add(KaraffeSource.fromPath(Paths.get(arg)));
                } catch (IOException e) {
                    throw new UncheckedIOException(Paths.get(arg).toAbsolutePath().toString(), e);
                }
                continue;
            }

            boolean added = false;
            switch (arg) {
            case "--dry-run":
                added |= this.flags.add(arg.replace("--", ""));
                break;
            default:
                this.add(Report.newErrorReport("Unrecognized option : " + arg).build());
                this.hasError = true;
                added = true;
            }
            if (!added) {
                this.hasError = true;
                this.add(Report.newErrorReport("Duplicated flag : " + arg).build());
            }
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

    public boolean hasFlag(String flagName) {
        return this.flags.contains(flagName);
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
