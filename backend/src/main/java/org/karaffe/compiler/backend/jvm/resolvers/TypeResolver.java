package org.karaffe.compiler.backend.jvm.resolvers;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;

import org.karaffe.compiler.ast.names.PackageName;

import karaffe.core.Any;

public final class TypeResolver {

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

    public static Optional<List<Class<?>>> findClassesInPackage(PackageName packageName) {
        return findClassesInPackage(packageName.toString());
    }

    public static Optional<List<Class<?>>> findClassesInPackage(String packageName) {
        List<Class<?>> classes = new ArrayList<>();
        findClassesInPackageFromClassPath(packageName).ifPresent(classes::addAll);
        findClassesInPackageFromClassLoader(packageName).ifPresent(classes::addAll);
        return Optional.of(classes);
    }

    public static Optional<List<Class<?>>> findClassesInPackageFromClassLoader(String packageName) {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            String resourceName = packageName.replace(".", "/");
            Enumeration<URL> resources = contextClassLoader.getResources(resourceName);
            while (resources.hasMoreElements()) {
                URL url = resources.nextElement();
                String protocol = url.getProtocol().toLowerCase();
                if (protocol.equals("file")) {
                    File[] classFiles = new File(url.getFile()).listFiles((dir, name) -> name.endsWith(".class"));
                    return Optional.of(Stream.of(classFiles)
                            .map(File::getName)
                            .map(fileName -> fileName.replace(".class", ""))
                            .map(name -> packageName + "." + name)
                            .map(TypeResolver::findClass)
                            .map(Optional::get)
                            .collect(Collectors.toList()));
                } else if (protocol.equals("jar")) {
                    try (JarFile jarFile = ((JarURLConnection) url.openConnection()).getJarFile()) {
                        return Optional.of(Collections.list(jarFile.entries()).stream()
                                .map(JarEntry::getName)
                                .filter(name -> name.startsWith(resourceName) && name.endsWith(".class"))
                                .map(name -> name.replace('/', '.').replace(".class", ""))
                                .map(TypeResolver::findClass)
                                .map(Optional::get)
                                .collect(Collectors.toList()));
                    }
                } else {
                    throw new IllegalStateException("Unknown protocol : " + protocol);
                }

            }
        } catch (IOException e) {
            return Optional.empty();
        }
        return Optional.empty();
    }

    public static Optional<List<Class<?>>> findClassesInPackageFromClassPath(String packageName) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        try (StandardJavaFileManager fileManager = compiler.getStandardFileManager(new DiagnosticCollector<>(), null, null)) {
            Set<JavaFileObject.Kind> kind = new HashSet<>();
            kind.add(JavaFileObject.Kind.CLASS);

            List<Class<?>> classes = new ArrayList<>();
            Pattern pattern = Pattern.compile("(\\w+)\\.class");
            for (JavaFileObject f : fileManager.list(StandardLocation.PLATFORM_CLASS_PATH, packageName, kind, false)) {
                String classFilePathName = f.getName();
                Matcher matcher = pattern.matcher(classFilePathName);
                matcher.find();
                String className = matcher.group(1);
                TypeResolver.findClass(packageName + "." + className).ifPresent(classes::add);
            }
            return Optional.of(classes);
        } catch (IOException e) {
            return Optional.empty();
        }
    }

}
