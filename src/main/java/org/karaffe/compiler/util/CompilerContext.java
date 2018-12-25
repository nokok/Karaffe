package org.karaffe.compiler.util;

import org.karaffe.compiler.args.ArgsParser;
import org.karaffe.compiler.args.Flag;
import org.karaffe.compiler.args.Options;
import org.karaffe.compiler.args.ParameterName;
import org.karaffe.compiler.report.Report;
import org.karaffe.compiler.report.ReportFormatter;
import org.karaffe.compiler.tree.NodeType;
import org.karaffe.compiler.tree.Tree;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class CompilerContext {
    private String[] rawArgs = new String[0];
    private Options options = new Options();
    private List<KaraffeSource> sources = new ArrayList<>();
    private List<Report> reports = new ArrayList<>();
    private Map<Path, byte[]> outputFiles = new HashMap<>();
    private ClassLoader defaultClassLoader = Thread.currentThread().getContextClassLoader();
    private DynamicClassLoader dynamicClassLoader = new DynamicClassLoader(defaultClassLoader);
    private Tree currentAST = new Tree(NodeType.Error, "DEFAULT", null);

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

    public Optional<KaraffeSource> getSource(String sourceName) {
        if (this.sources.isEmpty()) {
            return Optional.empty();
        }
        if (this.sources.size() == 1) {
            return Optional.of(this.sources.get(0));
        }
        return this.sources.stream().filter(s -> s.getSourceName().equals(sourceName)).findFirst();
    }

    public void add(BytecodeEntry entry) {
        Objects.requireNonNull(entry);
        this.dynamicClassLoader.define(entry.getPath().getFileName().toString().replace(".class", ""), entry.getByteCode());
        this.outputFiles.put(entry.getPath(), entry.getByteCode());
    }

    public void add(Report report) {
        this.reports.add(Objects.requireNonNull(report));
    }

    public Map<Path, byte[]> getOutputFiles() {
        return outputFiles;
    }

    public boolean requireShowUsage() {
        return (this.rawArgs.length == 0 && this.getSources().isEmpty()) || this.hasError || this.hasFlag(Flag.HELP);
    }

    public boolean hasFlag(Flag flagName) {
        return this.options.hasFlag(flagName);
    }

    public Optional<String> getParameter(ParameterName parameterName) {
        return this.options.getParameter(parameterName);
    }

    public String getOutputText() {
        List<String> reportTexts = new ArrayList<>();
        ReportFormatter formatter = new ReportFormatter(this);
        for (Report report : this.reports) {
            reportTexts.add(formatter.format(report));
        }
        return String.join("\n", reportTexts);
    }

    public Tree getCurrentAST() {
        return currentAST;
    }

    public void setAST(Tree currentAST) {
        this.currentAST = currentAST;
    }
}
