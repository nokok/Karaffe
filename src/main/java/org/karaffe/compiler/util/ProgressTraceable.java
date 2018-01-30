package org.karaffe.compiler.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface ProgressTraceable {
    final Logger PROGRESS = LoggerFactory.getLogger("progress");

    public default void errorReport(final String msg) {
        ProgressTraceable.PROGRESS.error(msg);
    }

    public default void errorReport(final String format, final Object arg) {
        ProgressTraceable.PROGRESS.error(format, arg);
    }

    public default void errorReport(final String format, final Object... arguments) {
        ProgressTraceable.PROGRESS.error(format, arguments);
    }

    public default void errorReport(final String format, final Object arg1, final Object arg2) {
        ProgressTraceable.PROGRESS.error(format, arg1, arg2);
    }

    public default void errorReport(final String msg, final Throwable t) {
        ProgressTraceable.PROGRESS.error(msg, t);
    }

    public default void progress(final String msg) {
        ProgressTraceable.PROGRESS.info(msg);
    }

    public default void progress(final String format, final Object arg) {
        ProgressTraceable.PROGRESS.info(format, arg);
    }

    public default void progress(final String format, final Object... arguments) {
        ProgressTraceable.PROGRESS.info(format, arguments);
    }

    public default void progress(final String format, final Object arg1, final Object arg2) {
        ProgressTraceable.PROGRESS.info(format, arg1, arg2);
    }

    public default void progress(final String msg, final Throwable t) {
        ProgressTraceable.PROGRESS.info(msg, t);
    }

    public default void warningReport(final String msg) {
        ProgressTraceable.PROGRESS.warn(msg);
    }

    public default void warningReport(final String format, final Object arg) {
        ProgressTraceable.PROGRESS.warn(format, arg);
    }

    public default void warningReport(final String format, final Object... arguments) {
        ProgressTraceable.PROGRESS.warn(format, arguments);
    }

    public default void warningReport(final String format, final Object arg1, final Object arg2) {
        ProgressTraceable.PROGRESS.warn(format, arg1, arg2);
    }

    public default void warningReport(final String msg, final Throwable t) {
        ProgressTraceable.PROGRESS.warn(msg, t);
    }

}
