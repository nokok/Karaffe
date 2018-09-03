package org.karaffe.compiler.base.util.config;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;

import java.util.ArrayList;
import java.util.List;

public class Options {

    @Option(name = "--diag", usage = "Show diagnostic data", handler = MyBooleanOptionHandler.class)
    public boolean showDiag;

    @Option(name = "-v", aliases = "--version", usage = "Print compiler version and exit", handler = MyBooleanOptionHandler.class)
    public boolean showVersion;

    @Option(name = "-i", aliases = "--info", usage = "Set output log levels INFO and higher", handler = MyBooleanOptionHandler.class)
    public boolean isInfoLog;

    @Option(name = "-d", aliases = "--debug", usage = "Set output log levels DEBUG and higher", handler = MyBooleanOptionHandler.class)
    public boolean isDebugLog;

    @Option(name = "--trace", usage = "Set output log levels TRACE and higher", handler = MyBooleanOptionHandler.class)
    public boolean isTraceLog;

    @Option(name = "--show-tasks", usage = "Print all tasks", handler = MyBooleanOptionHandler.class)
    public boolean showTasks;

    @Option(name = "--print-tree", usage = "Print Tree every phase", handler = MyBooleanOptionHandler.class)
    public boolean printTree;

    @Option(name = "-h", aliases = {"--help", "-help"}, usage = "Show usage", handler = MyBooleanOptionHandler.class)
    public boolean showHelp;

    @Option(name = "--stop-after", metaVar = "<task>", usage = "Stop and Print tree at <task>", handler = MyStringOptionHandler.class)
    public String stopTaskName;

    @Option(name = "--print-last-tree", usage = "Print tree", handler = MyBooleanOptionHandler.class)
    public boolean showLastTree;

    @Option(name = "--skip-pkg-check", usage = "Skip checking of correspondence between package decl and file structure", handler = MyBooleanOptionHandler.class)
    public boolean skipPackageCheck;

    /* Hidden options */
    @Option(name = "--dump-mir", hidden = true, usage = "Parse and type-check input file(s) and dump AST")
    public boolean dumpMIR;

    @Option(name = "--dump-tables", hidden = true, usage = "Dump local variables table and methods table")
    public boolean dumpTables;

    @Option(name = "--use-default-antlr-listener", hidden = true, handler = MyBooleanOptionHandler.class)
    public boolean useDefaultANTLRListener = false;

    @Option(name = "--backend", hidden = true)
    public String backendName = "jvm";

    @Option(name = "--frontend", hidden = true)
    public String frontendName = "karaffe";

    @Argument
    public List<String> arguments = new ArrayList<>();

    @Override
    public String toString() {
        return "Options{" +
                "showDiag=" + showDiag +
                ", showVersion=" + showVersion +
                ", isInfoLog=" + isInfoLog +
                ", isDebugLog=" + isDebugLog +
                ", isTraceLog=" + isTraceLog +
                ", showTasks=" + showTasks +
                ", printTree=" + printTree +
                ", showHelp=" + showHelp +
                ", stopTaskName=" + stopTaskName +
                ", showLastTree=" + showLastTree +
                ", skipPackageCheck=" + skipPackageCheck +
                ", useDefaultANTLRListener=" + useDefaultANTLRListener +
                ", backendName=" + backendName +
                ", arguments=" + arguments +
                '}';
    }
}