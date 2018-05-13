package org.karaffe.compiler.base;

import org.karaffe.compiler.base.context.CommandLineOptions;
import org.karaffe.compiler.base.util.config.Options;
import org.kohsuke.args4j.CmdLineException;

import java.io.PrintStream;
import java.util.Objects;

public class CompilerContext {

    private final CommandLineOptions commandLineOptions;
    private boolean hasInvalidCmdLineArg;

    public CompilerContext() {
        this(new String[0]);
    }

    public CompilerContext(String[] args) {
        Objects.requireNonNull(args);
        this.commandLineOptions = new CommandLineOptions(args);
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

    @SuppressWarnings("unused")
    public void printUsage(PrintStream printStream) {
        commandLineOptions.printUsage(printStream);
    }
}
