package org.karaffe.compiler.util;

import org.karaffe.compiler.tree.NodeType;
import org.karaffe.compiler.tree.Tree;
import org.karaffe.compiler.tree.TreeFactory;
import org.karaffe.compiler.util.args.ArgsParser;
import org.karaffe.compiler.util.args.Flag;
import org.karaffe.compiler.util.args.Options;
import org.karaffe.compiler.util.args.ParameterName;
import org.karaffe.compiler.util.report.Report;
import org.karaffe.compiler.util.report.ReportFormatter;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class CompilerContext {

  private final ClassLoader defaultClassLoader = Thread.currentThread().getContextClassLoader();
  private final DynamicClassLoader dynamicClassLoader = new DynamicClassLoader(defaultClassLoader);

  private String[] rawArgs = new String[0];
  private Options options = new Options();
  private List<KaraffeSource> sources = new ArrayList<>();
  private List<Report> reports = new ArrayList<>();
  private Map<Path, byte[]> outputFiles = new HashMap<>();
  private Tree untypedTree = TreeFactory.newTree(NodeType.Error, Position.noPos());

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

  public boolean add(BytecodeEntry entry) {
    Objects.requireNonNull(entry);
    if (this.outputFiles.containsKey(entry.getPath())) {
      return false;
    }
    this.outputFiles.put(entry.getPath(), entry.getByteCode());
    this.dynamicClassLoader.define(entry.getPath().getFileName().toString().replace(".class", ""), entry.getByteCode());
    return true;
  }

  public void add(Report report) {
    this.reports.add(Objects.requireNonNull(report));
    if (report.isError()) {
      this.hasError = true;
    }
  }

  public Map<Path, byte[]> getOutputFiles() {
    return outputFiles;
  }

  public boolean requireShowUsage() {
    return (this.rawArgs.length == 0 || this.getSources().isEmpty()) || this.hasError || this.hasFlag(Flag.HELP);
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

  public Tree getUntypedTree() {
    return untypedTree;
  }

  public void setUntypedTree(Tree untypedTree) {
    this.untypedTree = untypedTree;
  }
}
