package org.karaffe.compiler.frontend.karaffe.tasks;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.util.Platform;
import org.karaffe.compiler.frontend.karaffe.transformer.util.Result;
import org.kohsuke.args4j.CmdLineParser;

public class ShowUsageTask implements Task {
    @Override
    public String name() {
        return "Show usage";
    }

    @Override
    public String description() {
        return "";
    }

    @Override
    public Result run(CompilerContext context) {
        CmdLineParser commandLineParser = context.getCommandLineParser();
        Platform.stdOut("Usage:");
        Platform.stdOut(" krfc <options> <sources>");
        Platform.stdOut();
        Platform.stdOut("Available options are:");
        commandLineParser.printUsage(Platform.getStdOut());
        return Result.SUCCESS;
    }

    @Override
    public boolean isRunnable(CompilerContext context) {
        return context.hasInvalidArg();
    }

    @Override
    public boolean changed() {
        return false;
    }
}
