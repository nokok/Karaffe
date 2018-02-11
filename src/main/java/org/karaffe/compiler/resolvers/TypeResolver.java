package org.karaffe.compiler.resolvers;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.karaffe.compiler.tree.Select;
import org.karaffe.compiler.tree.base.Node;

import karaffe.core.Any;

public final class TypeResolver {
    private TypeResolver() {

    }

    public static boolean isValidFQCN(String fqcn) {
        return findClass(Objects.requireNonNull(fqcn)).isPresent();
    }

    public static boolean isInValidFQCN(String fqcn) {
        return !isValidFQCN(fqcn);
    }

    public static Optional<Class<?>> findClass(String fqcn) {
        try {
            Class<?> clazz = Class.forName(Objects.requireNonNull(fqcn));
            return Optional.of(clazz);
        } catch (ClassNotFoundException ex) {
            return Optional.empty();
        }
    }

    public static Optional<List<String>> buildSuperClasses(String fqcn) {
        if (isInValidFQCN(fqcn)) {
            return Optional.empty();
        }
        List<String> classes = new ArrayList<>();
        if (fqcn.equals("java.lang.Object")) {
            return Optional.ofNullable(classes);
        }
        Class<?> clazz = findClass(fqcn).get();
        Class<?> current = clazz;
        while (!current.equals(Object.class)) {
            Class<?> superclass = current.getSuperclass();
            classes.add(superclass.getCanonicalName());
            current = superclass;
        }
        return Optional.of(classes);
    }

    public static Optional<List<String>> findAllCompatibleClasses(Class<? extends Any> clazz) {
        return findAllCompatibleClasses(clazz.getCanonicalName());
    }

    public static Optional<List<String>> findAllCompatibleClasses(String fqcn) {
        List<String> classes = new ArrayList<>();
        classes.add(fqcn);
        buildImplementedInterfaces(fqcn).ifPresent(classes::addAll);
        List<String> superClasses = buildSuperClasses(fqcn).get();
        for (String canonicalName : superClasses) {
            buildImplementedInterfaces(canonicalName).ifPresent(classes::addAll);
            classes.add(canonicalName);
        }
        ArrayList<String> ret = new ArrayList<>(new LinkedHashSet<>(classes));
        return Optional.of(ret);
    }

    public static Optional<List<String>> buildImplementedInterfaces(String fqcn) {
        List<String> classes = new ArrayList<>();
        Class<?> clazz = findClass(fqcn).get();
        classes.add(fqcn);
        for (Class<?> in : clazz.getInterfaces()) {
            classes.addAll(buildImplementedInterfaces(in.getCanonicalName()).orElse(new ArrayList<>(0)));
        }
        return Optional.of(classes);
    }

    public static Optional<List<Node>> findMethodArgsType(Class<?> clazz, String methodName) {
        List<Method> methods = Stream.of(clazz.getMethods()).filter(method -> method.getName().equals(methodName)).collect(Collectors.toList());
        if (methods.isEmpty()) {
            return Optional.empty();
        }
        if (methods.size() == 1) {
            // No overloads
            Method method = methods.get(0);
            List<Node> parameterTypes = Stream.of(method.getParameterTypes())
                    .map(Class::getCanonicalName)
                    .map(s -> s.split("\\."))
                    .map(Select::new)
                    .map(Node.class::cast)
                    .collect(Collectors.toList());
            return Optional.of(parameterTypes);
        }
        throw new UnsupportedOperationException("Overload not supported");
    }

}
