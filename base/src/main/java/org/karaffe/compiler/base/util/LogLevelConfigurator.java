package org.karaffe.compiler.base.util;


import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.stream.Stream;

public class LogLevelConfigurator {

    private final String[] args;

    public LogLevelConfigurator(String[] args) {
        this.args = args;
    }

    public void update() {
        Optional<String> logLevelConfig = Stream.of(args).filter(arg -> arg.startsWith("--log:")).findFirst();
        Level logLevel = logLevelConfig
                .map(arg -> arg.replace("--log:", ""))
                .map(String::toUpperCase)
                .map(Level::valueOf)
                .orElse(Level.OFF);

        if (Stream.of(args).anyMatch(arg -> arg.equals("--debug"))) {
            logLevel = Level.DEBUG;
        }
        if (Stream.of(args).anyMatch(arg -> arg.equals("--info"))) {
            logLevel = Level.INFO;
        }
        if (Stream.of(args).anyMatch(arg -> arg.equals("--trace"))) {
            logLevel = Level.TRACE;
        }
        Logger rootLogger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        rootLogger.setLevel(logLevel);
    }
}