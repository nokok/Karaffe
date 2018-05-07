package org.karaffe.compiler.base;

import org.karaffe.compiler.base.util.config.Options;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.ParserProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public enum CompilerContext {
    CONTEXT,;

    private String[] args;
    private String state;

    /* Command line options */
    private ParserProperties commandLineParserProperties;
    private CmdLineParser commandLineParser;
    public Options cmdLineOptions = new Options();
    private boolean hasInvalidArg;

    public boolean hasInvalidArg() {
        return hasInvalidArg;
    }

    public void setInvalidArg() {
        this.hasInvalidArg = true;
    }

    public void setArgs(String[] args) {
        if (this.args != null) {
            throw new IllegalStateException();
        }
        this.args = args;
    }

    public String[] getArgs() {
        return this.args;
    }

    public void setState(Class<?> phaseClass) {
        this.state = phaseClass.getCanonicalName();
    }

    @Override
    public String toString() {
        List<String> lines = new ArrayList<>();
        return String.join("\n", lines);
    }

    public void setCommandLineParserProperties(ParserProperties properties) {
        this.commandLineParserProperties = Objects.requireNonNull(properties);
    }

    public void setCommandLineParser(CmdLineParser cmdLineParser) {
        this.commandLineParser = Objects.requireNonNull(cmdLineParser);
    }

    public CmdLineParser getCommandLineParser() {
        if (this.commandLineParser == null) {
            throw new NullPointerException("CmdLineParser is null");
        }
        return this.commandLineParser;
    }


}
