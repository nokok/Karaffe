/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.asm;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AvailableTypes {

    private final Map<String, String> importMap = new HashMap<>();

    public static final String JAVA_OBJECT_CLASS = "java/lang/Object";

    public AvailableTypes() {
        defaultImports();
    }

    private void defaultImports() {
        importMap.put("String", "java/lang/String");
        importMap.put("Int", "java/lang/Integer");
        importMap.put("Double", "java/lang/Double");
        importMap.put("Any", "java/lang/Object");
    }

    public Optional<String> resolve(String className) {
        return Optional.ofNullable(importMap.get(className));
    }

    public void addImport(String shortName, String fullName) {
        URL url = ClassLoader.getSystemResource(fullName + ".class");
        if (url == null) {
            //TypeNotFound
            throw new RuntimeException();
        }
        importMap.put(shortName, fullName);
    }

    public void removeImport(String shortOrFullName) {

    }

    public void clear() {
        importMap.clear();
        defaultImports();
    }
}
