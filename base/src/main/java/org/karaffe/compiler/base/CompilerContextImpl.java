package org.karaffe.compiler.base;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.ParserRuleContext;
import org.karaffe.compiler.base.context.CommandLineOptions;
import org.karaffe.compiler.base.ir.IR;
import org.karaffe.compiler.base.report.Report;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.util.SourceFile;
import org.karaffe.compiler.base.util.config.Options;
import org.kohsuke.args4j.CmdLineException;

import java.io.PrintStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

public class CompilerContextImpl implements CompilerContext {

    private final CommandLineOptions commandLineOptions;
    private final Set<SourceFile> sourceFiles;
    private final Set<Lexer> lexers;
    private final Set<ParserRuleContext> contexts;
    private BackendType backendType;
    private FrontendType frontendType;
    private IR ir;
    private String state = "";
    private Tree compilationUnit;
    private boolean hasInvalidCmdLineArg;
    private List<Report> reports;
    private Map<Path, byte[]> byteCodeMap;

    public CompilerContextImpl() {
        this(new String[0]);
    }

    public CompilerContextImpl(String[] args) {
        Objects.requireNonNull(args);
        this.commandLineOptions = new CommandLineOptions(args);
        this.sourceFiles = new HashSet<>(args.length);
        this.lexers = new HashSet<>(args.length);
        this.contexts = new HashSet<>();
        this.hasInvalidCmdLineArg = false;
        this.reports = new ArrayList<>();
        this.byteCodeMap = new HashMap<>();
        this.frontendType = FrontendType.KARAFFE;
        this.backendType = BackendType.JVM;
    }

    @Override
    public void startParseArgs() throws CmdLineException {
        try {
            commandLineOptions.parseArgs();
        } catch (CmdLineException e) {
            this.hasInvalidCmdLineArg = true;
            throw e;
        }
    }

    @Override
    public String getState() {
        return this.state;
    }

    @Override
    public void setState(String state) {
        this.state = Objects.requireNonNull(state);
    }

    @Override
    public boolean hasInvalidCmdLineArg() {
        return hasInvalidCmdLineArg;
    }

    @Override
    public void setInvalidCmdLineArg() {
        this.hasInvalidCmdLineArg = true;
    }

    @Override
    public boolean isEmptyRawArg() {
        return this.commandLineOptions.isEmptyArgs();
    }

    @Override
    public Options getCmdLineOptions() {
        return this.commandLineOptions.get();
    }

    @Override
    public void printUsage() {
        commandLineOptions.printUsage();
    }

    @Override
    public void addSourceFile(SourceFile sourceFile) {
        this.sourceFiles.add(Objects.requireNonNull(sourceFile));
    }

    @Override
    public Stream<SourceFile> sourceFileStream() {
        return this.sourceFiles.stream();
    }

    @Override
    public void addLexer(Lexer lexer) {
        this.lexers.add(Objects.requireNonNull(lexer));
    }

    @Override
    public Stream<CommonTokenStream> tokenStreamStream() {
        return this.lexers.stream().map(CommonTokenStream::new);
    }

    @Override
    public void addContext(ParserRuleContext context) {
        this.contexts.add(Objects.requireNonNull(context));
    }

    @Override
    public Stream<ParserRuleContext> contextStream() {
        return this.contexts.stream();
    }

    @Override
    public Tree getCompilationUnit() {
        return this.compilationUnit;
    }

    @Override
    public void setCompilationUnit(Tree compilationUnit) {
        this.compilationUnit = Objects.requireNonNull(compilationUnit);
    }

    @Override
    public void printUsage(PrintStream printStream) {
        commandLineOptions.printUsage(printStream);
    }

    @Override
    public String toString() {
        return "CompilerContext{" +
                "state=" + state +
                ", commandLineOptions=" + commandLineOptions +
                ", sourceFiles=" + sourceFiles +
                ", lexers=" + lexers +
                ", contexts=" + contexts +
                ", compilationUnit=" + compilationUnit +
                ", hasInvalidCmdLineArg=" + hasInvalidCmdLineArg +
                '}';
    }

    @Override
    public IR getIR() {
        return this.ir;
    }

    @Override
    public void setIR(IR ir) {
        this.ir = Objects.requireNonNull(ir);
    }

    @Override
    public void addReport(Report report) {
        this.reports.add(Objects.requireNonNull(report));
    }

    @Override
    public List<Report> getReports() {
        return this.reports;
    }

    @Override
    public void addBytecode(Path filePath, byte[] bytecode) {
        this.byteCodeMap.put(Objects.requireNonNull(filePath), Objects.requireNonNull(bytecode));
    }

    @Override
    public Map<Path, byte[]> getBytecodes() {
        return this.byteCodeMap;
    }

    @Override
    public BackendType getTargetBackendType() {
        return this.backendType;
    }

    @Override
    public void setTargetBackendType(BackendType backendType) {
        this.backendType = Objects.requireNonNull(backendType);
    }

    @Override
    public FrontendType getFrontendType() {
        return this.frontendType;
    }

    @Override
    public void setFrontendType(FrontendType frontendType) {
        this.frontendType = Objects.requireNonNull(frontendType);
    }
}
