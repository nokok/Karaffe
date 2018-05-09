package org.karaffe.compiler.frontend.karaffe.tasks.options;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.util.config.Options;
import org.karaffe.compiler.frontend.karaffe.tasks.AbstractTask;
import org.karaffe.compiler.frontend.karaffe.tasks.TaskRunner;
import org.karaffe.compiler.frontend.karaffe.tasks.util.TaskResult;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.ParserProperties;
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
        ParserProperties properties = ParserProperties.defaults().withUsageWidth(999);
        Options options = context.cmdLineOptions;
        CmdLineParser cmdLineParser = new CmdLineParser(options, properties);
        context.setCommandLineParserProperties(properties);
        context.setCommandLineParser(cmdLineParser);

        if (context.getArgs().length == 0) {
            context.setInvalidArg();
            LOGGER.warn("Empty args");
            triggerSuccess();
            return TaskResult.SUCCESS_WITH_WARN;
        }

        try {
            cmdLineParser.parseArgument(context.getArgs());
            TaskRunner subTaskRunner = TaskRunner.newDefaultTaskRunner(context);
            subTaskRunner.standBy(CheckLogLevelTask::new, context::setInvalidArg, () -> {
            });
            subTaskRunner.standBy(CheckFileTask::new, context::setInvalidArg, () -> {
            });
            subTaskRunner.runAll();
            triggerSuccess();
            return TaskResult.SUCCESS;
        } catch (CmdLineException e) {
            LOGGER.error("Unexpected Exception", e);
            context.setInvalidArg();
            triggerFailure();
            return TaskResult.NON_RECOVERABLE;
        }
    }

    @Override
    public boolean changed() {
        return true;
    }
}
