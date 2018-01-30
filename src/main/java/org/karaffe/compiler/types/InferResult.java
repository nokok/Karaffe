package org.karaffe.compiler.types;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import karaffe.core.Any;

public class InferResult {

    public static String defaultTypeName() {
        return Any.class.getCanonicalName();
    }

    public static InferResult of(final Class<? extends Any> clazz) {
        return new InferResult(clazz.getCanonicalName());
    }

    public static InferResult ofEmpty() {
        return new InferResult("karaffe.core.Any");
    }

    private final List<String> types;

    public InferResult(final String... possibleTypes) {
        this.types = new ArrayList<>(Arrays.asList(possibleTypes));
    }

    public Optional<String> getType() {
        if (this.types.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(this.types.get(0));
    }

}
