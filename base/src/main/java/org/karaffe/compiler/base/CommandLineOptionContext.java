package org.karaffe.compiler.base;

import org.karaffe.compiler.base.util.config.Options;
import org.kohsuke.args4j.CmdLineException;

import java.io.PrintStream;

public interface CommandLineOptionContext {

    void startParseArgs() throws CmdLineException;

    boolean hasInvalidCmdLineArg();

    void setInvalidCmdLineArg();

    boolean isEmptyRawArg();

    Options getCmdLineOptions();

    void printUsage();

    void printUsage(PrintStream printStream);
}
