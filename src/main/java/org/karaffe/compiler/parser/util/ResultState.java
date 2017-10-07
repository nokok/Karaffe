package org.karaffe.compiler.parser.util;

public interface ResultState {

    boolean isSuccess();

    public default boolean isFailure() {
        return !this.isSuccess();
    }

}