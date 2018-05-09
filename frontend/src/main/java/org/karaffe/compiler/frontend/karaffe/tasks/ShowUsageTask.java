package org.karaffe.compiler.frontend.karaffe.tasks;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.util.Platform;
import org.karaffe.compiler.frontend.karaffe.tasks.util.TaskResult;
import org.kohsuke.args4j.CmdLineParser;

public class ShowUsageTask extends AbstractTask {
    @Override
    public String name() {
        return "show usage";
    }

    @Override
    public String description() {
        return "";
    }

    @Override
    public TaskResult run(CompilerContext context) {
        CmdLineParser commandLineParser = context.getCommandLineParser();
        Platform.stdOut("Usage:");
        Platform.stdOut(" krfc <options> <sources>");
        Platform.stdOut();
        Platform.stdOut("Available options are:");
        commandLineParser.printUsage(Platform.getStdOut());
        triggerSuccess();
        return TaskResult.SUCCESS;
    }

    @Override
    public boolean isRunnable(CompilerContext context) {
        return context.hasInvalidArg() || context.cmdLineOptions.showHelp;
    }

    @Override
    public boolean changed() {
        return false;
    }
}
