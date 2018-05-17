package org.karaffe.compiler.frontend.karaffe.tasks.options;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.task.TaskResult;
import org.karaffe.compiler.frontend.karaffe.tasks.AbstractTask;
import org.kohsuke.args4j.CmdLineException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParseCommandLineOptionsTask extends AbstractTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(ParseCommandLineOptionsTask.class);

    @Override
    public String name() {
        return "cmd";
    }

    @Override
    public String description() {
        return "Parse command line options";
    }

    @Override
    public TaskResult run(CompilerContext context) {
        if (context.isEmptyRawArg()) {
            context.setInvalidCmdLineArg();
            LOGGER.warn("Empty args");
            return TaskResult.SUCCESS_WITH_WARN;
        }
        try {
            context.startParseArgs();
        } catch (CmdLineException e) {
            LOGGER.error("Unexpected Exception", e);
            return TaskResult.FAILED;
        }
        return TaskResult.SUCCESS;
    }

    @Override
    public boolean changed() {
        return true;
    }
}