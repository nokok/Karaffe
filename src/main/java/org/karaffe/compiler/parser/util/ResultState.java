package org.karaffe.compiler.parser.util;

public interface ResultState {

    public default boolean isFailure() {
        return !this.isSuccess();
    }

    boolean isSuccess();

}