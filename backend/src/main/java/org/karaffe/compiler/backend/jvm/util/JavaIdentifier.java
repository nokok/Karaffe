package org.karaffe.compiler.backend.jvm.util;

import java.util.Objects;

public class JavaIdentifier implements CharSequence {
    private final String identifier;

    public JavaIdentifier(String identifier) throws IllegalNameException {
        this.identifier = Objects.requireNonNull(identifier);
        boolean hasError = false;
        hasError |= identifier.isEmpty();
        hasError |= !Character.isJavaIdentifierStart(identifier.charAt(0));
        if (hasError) {
            throw new IllegalNameException(identifier);
        }
    }

    @Override
    public int length() {
        return this.identifier.length();
    }

    @Override
    public char charAt(int i) {
        return this.identifier.charAt(i);
    }

    @Override
    public CharSequence subSequence(int i, int i1) {
        return this.identifier.subSequence(i, i1);
    }
}
