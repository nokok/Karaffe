package org.karaffe.compiler.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface Traceable {

    final Logger INTERNAL_LOGGER = LoggerFactory.getLogger(Traceable.class);

    public default String getName() {
        return Traceable.INTERNAL_LOGGER.getName();
    }

    public default boolean isTraceEnabled() {
        return Traceable.INTERNAL_LOGGER.isTraceEnabled();
    }

    public default void trace(final String msg) {
        Traceable.INTERNAL_LOGGER.trace(msg);
    }

    public default void trace(final String format, final Object arg) {
        Traceable.INTERNAL_LOGGER.trace(format, arg);
    }

    public default void trace(final String format, final Object arg1, final Object arg2) {
        Traceable.INTERNAL_LOGGER.trace(format, arg1, arg2);
    }

    public default void trace(final String format, final Object... arguments) {
        Traceable.INTERNAL_LOGGER.trace(format, arguments);
    }

    public default void trace(final String msg, final Throwable t) {
        Traceable.INTERNAL_LOGGER.trace(msg, t);
    }

    public default boolean isDebugEnabled() {
        return Traceable.INTERNAL_LOGGER.isDebugEnabled();
    }

    public default void debug(final String msg) {
        Traceable.INTERNAL_LOGGER.debug(msg);
    }

    public default void debug(final String format, final Object arg) {
        Traceable.INTERNAL_LOGGER.debug(format, arg);
    }

    public default void debug(final String format, final Object arg1, final Object arg2) {
        Traceable.INTERNAL_LOGGER.debug(format, arg1, arg2);
    }

    public default void debug(final String format, final Object... arguments) {
        Traceable.INTERNAL_LOGGER.debug(format, arguments);
    }

    public default void debug(final String msg, final Throwable t) {
        Traceable.INTERNAL_LOGGER.debug(msg, t);
    }

    public default boolean isInfoEnabled() {
        return Traceable.INTERNAL_LOGGER.isInfoEnabled();
    }

    public default void info(final String msg) {
        Traceable.INTERNAL_LOGGER.info(msg);
    }

    public default void info(final String format, final Object arg) {
        Traceable.INTERNAL_LOGGER.info(format, arg);
    }

    public default void info(final String format, final Object arg1, final Object arg2) {
        Traceable.INTERNAL_LOGGER.info(format, arg1, arg2);
    }

    public default void info(final String format, final Object... arguments) {
        Traceable.INTERNAL_LOGGER.info(format, arguments);
    }

    public default void info(final String msg, final Throwable t) {
        Traceable.INTERNAL_LOGGER.info(msg, t);
    }

    public default boolean isWarnEnabled() {
        return Traceable.INTERNAL_LOGGER.isWarnEnabled();
    }

    public default void warn(final String msg) {
        Traceable.INTERNAL_LOGGER.warn(msg);
    }

    public default void warn(final String format, final Object arg) {
        Traceable.INTERNAL_LOGGER.warn(format, arg);
    }

    public default void warn(final String format, final Object... arguments) {
        Traceable.INTERNAL_LOGGER.warn(format, arguments);
    }

    public default void warn(final String format, final Object arg1, final Object arg2) {
        Traceable.INTERNAL_LOGGER.warn(format, arg1, arg2);
    }

    public default void warn(final String msg, final Throwable t) {
        Traceable.INTERNAL_LOGGER.warn(msg, t);
    }

    public default boolean isErrorEnabled() {
        return Traceable.INTERNAL_LOGGER.isErrorEnabled();
    }

    public default void error(final String msg) {
        Traceable.INTERNAL_LOGGER.error(msg);
    }

    public default void error(final String format, final Object arg) {
        Traceable.INTERNAL_LOGGER.error(format, arg);
    }

    public default void error(final String format, final Object arg1, final Object arg2) {
        Traceable.INTERNAL_LOGGER.error(format, arg1, arg2);
    }

    public default void error(final String format, final Object... arguments) {
        Traceable.INTERNAL_LOGGER.error(format, arguments);
    }

    public default void error(final String msg, final Throwable t) {
        Traceable.INTERNAL_LOGGER.error(msg, t);
    }

}
