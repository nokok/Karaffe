package org.karaffe.compiler.base.util.config;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;

import java.util.ArrayList;
import java.util.List;

public class Options {


    @Option(name = "--diag", usage = "show diagnostic data", handler = MyBooleanOptionHandler.class)
    public boolean showDiag;

    @Option(name = "--version", aliases = {"-v"}, usage = "Print compiler version and exit", handler = MyBooleanOptionHandler.class)
    public boolean showVersion;

    @Option(name = "--info", aliases = "-i", usage = "Set output log levels INFO and higher", handler = MyBooleanOptionHandler.class)
    public boolean isInfoLog;

    @Option(name = "--debug", aliases = "-d", usage = "Set output log levels DEBUG and higher", handler = MyBooleanOptionHandler.class)
    public boolean isDebugLog;

    @Option(name = "--trace", usage = "Set output log levels TRACE and higher", handler = MyBooleanOptionHandler.class)
    public boolean isTraceLog;

    @Option(name = "--show-phases", usage = "Print all phases", handler = MyBooleanOptionHandler.class)
    public boolean showPhases;

    @Option(name = "--print-tree", usage = "Print Tree every phase", handler = MyBooleanOptionHandler.class)
    public boolean printTree;

    @Option(name = "--help", aliases = {"-h", "-help"}, usage = "Show usage", handler = MyBooleanOptionHandler.class)
    public boolean showHelp;

    @Option(name = "--stop-after", metaVar = "<phase>", usage = "Stop and Print tree at <phase>")
    public String stopPhaseName;

    @Option(name = "--print-last-tree", usage = "Print tree", handler = MyBooleanOptionHandler.class)
    public boolean showLastTree;

    @Option(name = "--skip-pkg-check", usage = "skip checking of correspondence between package decl and file structure", handler = MyBooleanOptionHandler.class)
    public boolean skipPackageCheck;

    /* Hidden options */

    @Option(name = "--use-default-antlr-listener", hidden = true, handler = MyBooleanOptionHandler.class)
    public boolean useDefaultANTLRListener;

    @Argument
    public List<String> arguments = new ArrayList<>();
}
