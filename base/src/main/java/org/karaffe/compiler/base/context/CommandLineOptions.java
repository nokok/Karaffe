package org.karaffe.compiler.base.context;

import org.karaffe.compiler.base.util.Platform;
import org.karaffe.compiler.base.util.config.Options;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.ParserProperties;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandLineOptions {
    private final List<String> rawArgs;
    private Options options;
    private CmdLineParser parser;

    public CommandLineOptions(String[] args) {
        this(args, ParserProperties.defaults().withUsageWidth(999));
    }

    public CommandLineOptions(String[] args, ParserProperties parserProperties) {
        this.rawArgs = new ArrayList<>(Arrays.asList(args));
        this.options = new Options();
        this.parser = new CmdLineParser(options, parserProperties);
    }

    public boolean isEmptyArgs() {
        return this.rawArgs.isEmpty();
    }

    public void parseArgs() throws CmdLineException {
        this.parser.parseArgument(this.rawArgs);
    }

    public void printUsage() {
        printUsage(Platform.getStdOut());
    }

    public Options get() {
        return this.options;
    }

    public void printUsage(PrintStream printStream) {
        printStream.println("Usage:");
        printStream.println(" krfc <options> <sources>");
        printStream.println();
        printStream.println("Available options are:");
        this.parser.printUsage(printStream);
    }

}
