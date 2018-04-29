package org.karaffe.compiler.backend.jvm.resolvers;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConstructorResolver {
    public static Optional<List<Constructor<?>>> findConstructors(String anyName) {
        Objects.requireNonNull(anyName);
        if (!anyName.endsWith("<init>")) {
            return Optional.empty();
        }
        String className = anyName.replace(".<init>", "");
        Optional<Class<?>> clazzOpt = TypeResolver.findClass(className);
        return clazzOpt.map(clazz -> clazz.getConstructors())
                .map(Stream::of)
                .map(stream -> stream.collect(Collectors.toList()));
    }
}
