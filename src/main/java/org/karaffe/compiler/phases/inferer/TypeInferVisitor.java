package org.karaffe.compiler.phases.inferer;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.karaffe.compiler.tree.visitor.KaraffeTreeVisitorAdapter;

public class TypeInferVisitor extends KaraffeTreeVisitorAdapter {

    private final Map<String /**/, String /**/> definedMap = new HashMap<>();
    private final Map<String /* name */, String /* type */> selectedTypes = new HashMap<>();

    public Optional<String> getType(String name) {
        if (this.selectedTypes.containsKey(name)) {
            return Optional.ofNullable(this.selectedTypes.get(name));
        }
        return Optional.empty();
    }



}
