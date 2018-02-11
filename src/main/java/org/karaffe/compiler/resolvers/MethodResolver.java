package org.karaffe.compiler.resolvers;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.karaffe.compiler.tree.Name;

public class MethodResolver {
    private final Class<?> targetClass;

    public MethodResolver(Class<?> targetClass) {
        this.targetClass = targetClass;
    }

    public List<Method> findMethodsByMethodName(String methodName) {
        return Stream.of(this.targetClass.getMethods())
                .filter(method -> method.getName().equals(methodName))
                .collect(Collectors.toList());
    }

    public List<Method> findMethodsByMethodName(Name name) {
        return findMethodsByMethodName(name.getName());
    }

    public static Optional<List<Method>> findMethods(String anyString) {
        Objects.requireNonNull(anyString);
        if (anyString.isEmpty() || !anyString.contains(".")) {
            return Optional.empty();
        }
        List<String> names = new ArrayList<>(Arrays.asList(anyString.split("\\.")));
        String className = String.join(".", names.subList(0, names.size() - 1));
        String methodName = names.get(names.size() - 1);
        if (TypeResolver.isInValidFQCN(className)) {
            return Optional.empty();
        }
        Optional<Class<?>> clazzOpt = TypeResolver.findClass(className);
        return clazzOpt
                .map(clazz -> Stream.of(clazz.getMethods())
                        .filter(method -> method.getName().equals(methodName))
                        .collect(Collectors.toList()));
    }
}
