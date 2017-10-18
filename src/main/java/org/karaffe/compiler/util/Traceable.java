package org.karaffe.compiler.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface Traceable {
    Logger LOGGER = LoggerFactory.getLogger(Traceable.class);

    public default String getName() {
        return Traceable.LOGGER.getName();
    }

    public default boolean isTraceEnabled() {
        return Traceable.LOGGER.isTraceEnabled();
    }

    public default void trace(final String msg) {
        Traceable.LOGGER.trace(msg);
    }

    public default void trace(final String format, final Object arg) {
        Traceable.LOGGER.trace(format, arg);
    }

    public default void trace(final String format, final Object arg1, final Object arg2) {
        Traceable.LOGGER.trace(format, arg1, arg2);
    }

    public default void trace(final String format, final Object... arguments) {
        Traceable.LOGGER.trace(format, arguments);
    }

    public default void trace(final String msg, final Throwable t) {
        Traceable.LOGGER.trace(msg, t);
    }

    public default boolean isDebugEnabled() {
        return Traceable.LOGGER.isDebugEnabled();
    }

    public default void debug(final String msg) {
        Traceable.LOGGER.debug(msg);
    }

    public default void debug(final String format, final Object arg) {
        Traceable.LOGGER.debug(format, arg);
    }

    public default void debug(final String format, final Object arg1, final Object arg2) {
        Traceable.LOGGER.debug(format, arg1, arg2);
    }

    public default void debug(final String format, final Object... arguments) {
        Traceable.LOGGER.debug(format, arguments);
    }

    public default void debug(final String msg, final Throwable t) {
        Traceable.LOGGER.debug(msg, t);
    }

    public default boolean isInfoEnabled() {
        return Traceable.LOGGER.isInfoEnabled();
    }

    public default void info(final String msg) {
        Traceable.LOGGER.info(msg);
    }

    public default void info(final String format, final Object arg) {
        Traceable.LOGGER.info(format, arg);
    }

    public default void info(final String format, final Object arg1, final Object arg2) {
        Traceable.LOGGER.info(format, arg1, arg2);
    }

    public default void info(final String format, final Object... arguments) {
        Traceable.LOGGER.info(format, arguments);
    }

    public default void info(final String msg, final Throwable t) {
        Traceable.LOGGER.info(msg, t);
    }

    public default boolean isWarnEnabled() {
        return Traceable.LOGGER.isWarnEnabled();
    }

    public default void warn(final String msg) {
        Traceable.LOGGER.warn(msg);
    }

    public default void warn(final String format, final Object arg) {
        Traceable.LOGGER.warn(format, arg);
    }

    public default void warn(final String format, final Object... arguments) {
        Traceable.LOGGER.warn(format, arguments);
    }

    public default void warn(final String format, final Object arg1, final Object arg2) {
        Traceable.LOGGER.warn(format, arg1, arg2);
    }

    public default void warn(final String msg, final Throwable t) {
        Traceable.LOGGER.warn(msg, t);
    }

    public default boolean isErrorEnabled() {
        return Traceable.LOGGER.isErrorEnabled();
    }

    public default void error(final String msg) {
        Traceable.LOGGER.error(msg);
    }

    public default void error(final String format, final Object arg) {
        Traceable.LOGGER.error(format, arg);
    }

    public default void error(final String format, final Object arg1, final Object arg2) {
        Traceable.LOGGER.error(format, arg1, arg2);
    }

    public default void error(final String format, final Object... arguments) {
        Traceable.LOGGER.error(format, arguments);
    }

    public default void error(final String msg, final Throwable t) {
        Traceable.LOGGER.error(msg, t);
    }

}
