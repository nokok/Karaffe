package org.karaffe.compiler.types;

import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.karaffe.compiler.tree.Name;

public class MethodResolver {
    private final Class<?> targetClass;

    public MethodResolver(Class<?> targetClass) {
        this.targetClass = targetClass;
    }

    public List<Method> findMethods(String methodName) {
        return Stream.of(this.targetClass.getMethods())
                .filter(method -> method.getName().equals(methodName))
                .collect(Collectors.toList());
    }

    public List<Method> findMethods(Name name) {
        return findMethods(name.getName());
    }
}
