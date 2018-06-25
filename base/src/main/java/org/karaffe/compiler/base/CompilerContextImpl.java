package org.karaffe.compiler.base;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.ParserRuleContext;
import org.karaffe.compiler.base.context.CommandLineOptions;
import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.def.Def;
import org.karaffe.compiler.base.util.SourceFile;
import org.karaffe.compiler.base.util.config.Options;
import org.kohsuke.args4j.CmdLineException;

import java.io.PrintStream;
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
    private String state = "";
    private Tree compilationUnit;
    private boolean hasInvalidCmdLineArg;
    private Map<String, String> packageFileMap;
    private Map<String, List<Def>> fileImportMap;

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
        this.packageFileMap = new HashMap<>();
        this.fileImportMap = new HashMap<>();
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
    public void onPackageFilePair(String packageName, String relativeFilePath) {
        this.packageFileMap.put(Objects.requireNonNull(packageName), Objects.requireNonNull(relativeFilePath));
    }

    @Override
    @SuppressWarnings("unused")
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
    public void onFileImportDef(Position position, Def importDef) {
        if (position.isNoPos()) {
            return;
        }
        List<Def> defs;
        String sourceName = position.getSourceName();
        if (this.fileImportMap.containsKey(sourceName)) {
            defs = this.fileImportMap.get(sourceName);
        } else {
            defs = new ArrayList<>();
        }
        defs.add(importDef);
        this.fileImportMap.put(Objects.requireNonNull(sourceName), defs);
    }

    @Override
    public Map<String, List<Def>> getFileImportMap() {
        return fileImportMap;
    }

    @Override
    public Map<String, String> getPackageFileMap() {
        return packageFileMap;
    }
}
