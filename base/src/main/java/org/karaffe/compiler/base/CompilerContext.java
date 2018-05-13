package org.karaffe.compiler.base;

import org.antlr.v4.runtime.Lexer;
import org.karaffe.compiler.base.context.CommandLineOptions;
import org.karaffe.compiler.base.util.SourceFile;
import org.karaffe.compiler.base.util.config.Options;
import org.kohsuke.args4j.CmdLineException;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

public class CompilerContext {

    private final CommandLineOptions commandLineOptions;
    private final Set<SourceFile> sourceFiles;
    private final List<Lexer> lexers;
    private boolean hasInvalidCmdLineArg;

    public CompilerContext() {
        this(new String[0]);
    }

    public CompilerContext(String[] args) {
        Objects.requireNonNull(args);
        this.commandLineOptions = new CommandLineOptions(args);
        this.sourceFiles = new HashSet<>(args.length);
        this.lexers = new ArrayList<>();
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

    @SuppressWarnings("unused")
    public void printUsage(PrintStream printStream) {
        commandLineOptions.printUsage(printStream);
    }
}
