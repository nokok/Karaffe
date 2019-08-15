package org.karaffe.compiler.util;

import org.karaffe.compiler.tree.NodeType;
import org.karaffe.compiler.tree.Tree;
import org.karaffe.compiler.tree.TreeFactory;
import org.karaffe.compiler.util.args.ArgsParser;
import org.karaffe.compiler.util.args.Flag;
import org.karaffe.compiler.util.args.Options;
import org.karaffe.compiler.util.args.ParameterName;
import org.karaffe.compiler.util.report.Report;

import java.io.PrintStream;
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

  private Options options = new Options();
  private List<Report> reports = new ArrayList<>();
  private Map<Path, byte[]> outputFiles = new HashMap<>();
  private Tree untypedTree = TreeFactory.newTree(NodeType.Error, Position.noPos());

  private Map<String, Object> stateStore = new HashMap<>();

  private PrintStream stdOut;
  private PrintStream stdOutDebug;

  private boolean hasError = false;

  private CompilerContext() {

  }

  public static CompilerContext createInitialContext() {
    return new CompilerContext();
  }

  public static CompilerContext createInitialContext(StartupEnv env) {
    CompilerContext compilerContext = new CompilerContext();
    ArgsParser parser = new ArgsParser();
    compilerContext.options = parser.parse(env);
    compilerContext.stdOut = System.out;
    if (compilerContext.options.hasFlag(Flag.VERBOSE)) {
      compilerContext.stdOutDebug = System.out;
    } else {
      compilerContext.stdOutDebug = new NopPrintStream();
    }
    return compilerContext;
  }

  public boolean hasError() {
    return this.hasError;
  }

  public boolean hasSourceFile() {
    return !this.getSourceFiles().isEmpty();
  }

  public List<KaraffeSource> getSourceFiles() {
    return new ArrayList<>(this.options.getSources());
  }

  public Optional<KaraffeSource> getSource(String sourceName) {
    if (!this.hasSourceFile()) {
      return Optional.empty();
    }
    return this.getSourceFiles().stream().filter(s -> s.getSourceName().equals(sourceName)).findFirst();
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

  public List<Report> getReports() {
    return new ArrayList<>(this.reports);
  }

  public Map<Path, byte[]> getOutputFiles() {
    return outputFiles;
  }

  public boolean isEmptyArgs() {
    return this.options.isEmpty();
  }

  public boolean hasInvalidArgs() {
    return this.options.isInvalid();
  }

  public boolean hasFlag(Flag flag) {
    return options.hasFlag(flag);
  }

  public Optional<String> getParameter(ParameterName parameterName) {
    return Optional.empty();// TODO
  }

  public Tree getUntypedTree() {
    return untypedTree;
  }

  public void setUntypedTree(Tree untypedTree) {
    this.untypedTree = untypedTree;
  }

  public void writeLine(Object object) {
    this.stdOut.println(object);
  }

  public void writeDebug(Object object) {
    this.stdOutDebug.println(object);
  }

  public void set(String key, Object value) {
    this.stateStore.put(Objects.requireNonNull(key), Objects.requireNonNull(value));
  }

  public <T> T get(String key) {
    return (T) this.stateStore.get(key);
  }
}
