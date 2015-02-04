/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.asm.typechecker;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import net.nokok.karaffe.parser.excptn.CompilerException;
import net.nokok.karaffe.parser.util.ErrorType;
import org.objectweb.asm.Type;

public class ClassResolver {

    private final Map<String, Class<?>> importMap = new HashMap<>();
    private final List<String> defaultImportsPrefixes = Arrays.asList("java.lang.", "java.io.", "java.net.", "java.util.");

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
            throw new CompilerException(ErrorType.DEFAULT_IMPORTS_FAILED);
        }
    }

    public Optional<Class<?>> resolve(String className) {
        for (String importPrefix : defaultImportsPrefixes) {
            String path = importPrefix + className;
            try {
                return Optional.of(Class.forName(path));
            } catch (ClassNotFoundException ignored) {

            }
        }
        return Optional.ofNullable(importMap.get(className));
    }

    public Optional<Type> resolveType(String className) {
        Optional<Class<?>> clazz = resolve(className);
        return Optional.ofNullable(Type.getType(clazz.get()));
    }

    public void addImport(String shortName, String fullName) throws ClassNotFoundException {
        importMap.put(shortName, Class.forName(fullName));
    }

    public void clear() {
        importMap.clear();
        defaultImports();
    }
}
