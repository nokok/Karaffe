package org.karaffe.compiler.resolvers;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class StaticFieldResolver {
    public static Optional<Field> findStaticField(String anyName) {
        Objects.requireNonNull(anyName);
        if (anyName.isEmpty()) {
            return Optional.empty();
        }
        List<String> names = Arrays.asList(anyName.split("\\."));
        String className = String.join(".", names.subList(0, names.size() - 1));
        String fieldName = names.get(names.size() - 1);

        Optional<Class<?>> classOpt = TypeResolver.findClass(className);

        return classOpt.flatMap(clazz -> Stream.of(clazz.getFields())
                .filter(field -> Modifier.isStatic(field.getModifiers()))
                .filter(field -> {
                    return field.getName().equals(fieldName);
                })
                .findFirst());
    }
}
