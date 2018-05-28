package org.karaffe.compiler.base;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.ParserRuleContext;
import org.karaffe.compiler.base.context.CommandLineOptions;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.util.SourceFile;
import org.karaffe.compiler.base.util.config.Options;
import org.kohsuke.args4j.CmdLineException;

import java.io.PrintStream;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

public class CompilerContext {

    private String state = "";
    private final CommandLineOptions commandLineOptions;
    private final Set<SourceFile> sourceFiles;
    private final Set<Lexer> lexers;
    private final Set<ParserRuleContext> contexts;
    private Tree compilationUnit;
    private boolean hasInvalidCmdLineArg;

    public CompilerContext() {
        this(new String[0]);
    }

    public CompilerContext(String[] args) {
        Objects.requireNonNull(args);
        this.commandLineOptions = new CommandLineOptions(args);
        this.sourceFiles = new HashSet<>(args.length);
        this.lexers = new HashSet<>(args.length);
        this.contexts = new HashSet<>();
        this.hasInvalidCmdLineArg = false;
    }

    public void startParseArgs() throws CmdLineException {
        try {
            commandLineOptions.parseArgs();
        } catch (CmdLineException e) {
            this.hasInvalidCmdLineArg = true;
            throw e;
        }
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = Objects.requireNonNull(state);
    }

    public boolean hasInvalidCmdLineArg() {
        return hasInvalidCmdLineArg;
    }

    public void setInvalidCmdLineArg() {
        this.hasInvalidCmdLineArg = true;
    }

    public boolean isEmptyRawArg() {
        return this.commandLineOptions.isEmptyArgs();
    }

    public Options getCmdLineOptions() {
        return this.commandLineOptions.get();
    }

    public void printUsage() {
        commandLineOptions.printUsage();
    }

    public void addSourceFile(SourceFile sourceFile) {
        this.sourceFiles.add(Objects.requireNonNull(sourceFile));
    }

    public Stream<SourceFile> sourceFileStream() {
        return this.sourceFiles.stream();
    }

    public void addLexer(Lexer lexer) {
        this.lexers.add(Objects.requireNonNull(lexer));
    }

    public Stream<CommonTokenStream> tokenStreamStream() {
        return this.lexers.stream().map(CommonTokenStream::new);
    }

    public void addContext(ParserRuleContext context) {
        this.contexts.add(Objects.requireNonNull(context));
    }

    public Stream<ParserRuleContext> contextStream() {
        return this.contexts.stream();
    }

    public void setCompilationUnit(Tree compilationUnit) {
        this.compilationUnit = Objects.requireNonNull(compilationUnit);
    }

    public Tree getCompilationUnit() {
        return this.compilationUnit;
    }


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
}
