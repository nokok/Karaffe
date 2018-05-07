package org.karaffe.compiler.frontend.karaffe.tasks;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.util.config.Options;
import org.karaffe.compiler.frontend.karaffe.transformer.util.Result;
import org.slf4j.LoggerFactory;

public class ConfigureLogLevelTask implements Task {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ConfigureLogLevelTask.class);

    @Override
    public String name() {
        return "configure log level";
    }

    @Override
    public String description() {
        return "";
    }

    @Override
    public Result run(CompilerContext context) {
        Options options = context.cmdLineOptions;

        Level logLevel;
        if (options.isTraceLog) {
            logLevel = Level.TRACE;
        } else if (options.isDebugLog) {
            logLevel = Level.DEBUG;
        } else if (options.isInfoLog) {
            logLevel = Level.INFO;
        } else {
            logLevel = Level.OFF;
        }

        Logger rootLogger = (Logger) LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
        rootLogger.setLevel(logLevel);
        LOGGER.info("{} Logger is activated.", logLevel);
        return Result.SUCCESS;
    }

    @Override
    public boolean isRunnable(CompilerContext context) {
        return true;
    }

    @Override
    public boolean changed() {
        return false;
    }

}
