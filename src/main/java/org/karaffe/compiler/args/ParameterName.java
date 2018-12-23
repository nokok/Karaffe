package org.karaffe.compiler.args;

import java.util.Optional;

public enum ParameterName {
    EMIT(null, "--emit"),
    ;

    private final String shortName;
    private final String fullName;

    ParameterName(String shortName, String fullName) {
        this.shortName = shortName;
        this.fullName = fullName;
    }

    public Optional<String> getShortName() {
        return Optional.ofNullable(this.shortName);
    }

    public Optional<String> getFullName() {
        return Optional.ofNullable(this.fullName);
    }
}
