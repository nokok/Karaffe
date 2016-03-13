package karaffe.compiler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.objectweb.asm.Type;

public class ClassResolver {

    private final List<String> packageNames = new ArrayList<>();
    private final Map<String, String> aliases = new HashMap<>();

    public ClassResolver() {
        initNameAndAliases();
    }

    private void initNameAndAliases() {
        packageNames.clear();
        packageNames.add("karaffe.");
        packageNames.add("karaffe.compiler.");
        packageNames.add("java.lang.");
        packageNames.add("java.io.");
        packageNames.add("java.net.");
        packageNames.add("java.util.");
        packageNames.add("java.util.stream.");
        packageNames.add("java.util.concurrent.");
        packageNames.add("java.nio.");
        packageNames.add("java.nio.file.");
        packageNames.add("java.nio.channels.");

        aliases.clear();
        aliases.put("Int", "karaffe.core.Int");
        aliases.put("Bool", "java.lang.Boolean");
    }

    public Optional<String> resolveInternalNameByIdent(String ident) {
        return resolveTypeByIdent(ident).map(t -> t.getInternalName());
    }

    public Optional<String> resolveInternalNameByFullName(List<String> idents) {
        return resolveTypeByFullName(idents).map(t -> t.getInternalName());
    }

    public Optional<Type> resolveTypeByIdent(String ident) {
        return Optional.of(Type.getType(resolveClassByIdent(ident).orElse(Object.class)));
    }

    public Optional<Type> resolveTypeByFullName(List<String> idents) {
        return resolveClassByFullName(idents).map(c -> Type.getType(c));
    }

    @SuppressWarnings("unchecked")
    public Optional<Class<?>> resolveClassByIdent(String ident) {
        if ( aliases.containsKey(ident) ) {
            return findClass(aliases.get(ident));
        }
        return (Optional<Class<?>>) packageNames.stream()
            .map(name -> name + ident)
            .map(fqcn -> {
                try {
                    return Class.forName(fqcn);
                } catch (ClassNotFoundException e) {
                    return null;
                }
            }).filter(Objects::nonNull)
            .findFirst();
    }

    public Optional<Class<?>> resolveClassByFullName(List<String> idents) {
        StringBuilder path = new StringBuilder();
        idents.stream().forEach(ident -> path.append(ident).append("."));
        try {
            Class<?> clazz = Class.forName(path.toString());
            return Optional.ofNullable(clazz);
        } catch (ClassNotFoundException ex) {
            return Optional.empty();
        }
    }

    private Optional<Class<?>> findClass(String fqcn) {
        try {
            return Optional.of(Class.forName(fqcn));
        } catch (ClassNotFoundException ex) {
            return Optional.empty();
        }
    }

    public void clear() {
        initNameAndAliases();
    }
}
