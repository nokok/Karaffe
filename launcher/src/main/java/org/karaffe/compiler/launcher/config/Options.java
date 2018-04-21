package org.karaffe.compiler.launcher.config;

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

    @Argument
    public List<String> arguments = new ArrayList<>();
}
