/**
 * Karaffe Programming Language
 */
package karaffe.compiler.phase.resolvers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import org.objectweb.asm.Type;

public class ClassResolver {

    private final Map<String, Class<?>> importMap = new HashMap<>();
    private final List<String> defaultImportsPrefixes = Arrays.asList(
            "java.lang.",
            "java.io.",
            "java.net.",
            "java.util.",
            "java.util.stream.",
            "java.util.function.",
            "java.util.concurrent.",
            "java.nio.",
            "java.nio.file.",
            "java.nio.channels.");
    private final List<String> defaultImportClasses = Arrays.asList(
            "java.util.regex.Matcher",
            "java.util.regex.Pattern");

    public ClassResolver() {
        defaultImports();
    }

    public ClassResolver(Map<String, Class<?>> imported) {
        this();
        this.importMap.putAll(imported);
    }

    private void defaultImports() {
        try {
            importMap.put("String", Class.forName("java.lang.String"));
            importMap.put("Int", Class.forName("java.lang.Integer"));
            importMap.put("Double", Class.forName("java.lang.Double"));
            importMap.put("Any", Class.forName("java.lang.Object"));
            importMap.put("Void", Void.TYPE);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Class<?>> resolve(String className) {
        final Function<String, Optional<Class<?>>> strToClazz = str -> {
            try {
                return Optional.of(Class.forName(str));
            } catch (ClassNotFoundException ex) {
                return Optional.empty();
            }
        };
        {
            Optional<Class<?>> clazz = defaultImportClasses.stream()
                    .filter(name -> name.equals(className))
                    .map(strToClazz)
                    .map(Optional::get)
                    .findAny();
            if (clazz.isPresent()) {
                return clazz;
            }
        }
        {
            Optional<Class<?>> clazz = defaultImportsPrefixes.stream()
                    .map(prefix -> prefix + className)
                    .map(strToClazz)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .findFirst();
            if (clazz.isPresent()) {
                return clazz;
            }
        }
        return Optional.ofNullable(importMap.get(className));
    }

    public Optional<Type> resolveType(String className) {
        Optional<Class<?>> clazz = resolve(className);
        if (clazz.isPresent()) {
            return Optional.ofNullable(Type.getType(clazz.get()));
        } else {
            return Optional.empty();
        }
    }

    public void addImport(String shortName, String fullName) throws ClassNotFoundException {
        importMap.put(shortName, Class.forName(fullName));
    }

    public void clear() {
        importMap.clear();
        defaultImports();
    }
}
