package org.karaffe.compiler.tree.transform.typeinferer;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.karaffe.compiler.tree.transform.AbstractTransformer;
import org.karaffe.compiler.tree.v2.CompilationUnit;
import org.karaffe.compiler.tree.v2.Parameter;
import org.karaffe.compiler.tree.v2.imports.SimpleImport;
import org.karaffe.compiler.tree.v2.names.FullyQualifiedTypeName;
import org.karaffe.compiler.tree.v2.names.SimpleName;
import org.karaffe.compiler.tree.v2.statements.LetLocalDef;
import org.karaffe.compiler.types.v2.InferStates;
import org.karaffe.compiler.types.v2.states.InferStateType;
import org.karaffe.compiler.types.v2.states.Resolved;

public class TypeInferer extends AbstractTransformer {

    public TypeInferer() {
        super("type-inferer");
    }

    private Map<SimpleName, Class<?>> states = null;

    private final Map<SimpleName, FullyQualifiedTypeName> nameMaps = new HashMap<>();

    @Override
    public void onSimpleImportAfter(SimpleImport simpleImport) {
        super.onSimpleImportAfter(simpleImport);
        this.nameMaps.put(simpleImport.getImportedSimpleName(), simpleImport.getName());
    }

    @Override
    public void onCompilationUnitBefore(CompilationUnit compilationUnit) {
        TypeEnvironmentBuilder builder = new TypeEnvironmentBuilder();
        builder.transform(compilationUnit);
        Map<? extends SimpleName, Class<?>> resolvedMap = builder.getInferState()
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().getInferStateType().equals(InferStateType.RESOLVED))
                .map(entry -> new AbstractMap.SimpleEntry<>(entry.getKey(), ((Resolved) entry.getValue()).getSuitableType()))
                .collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));
        this.states = new HashMap<>(resolvedMap);
    }

    @Override
    public void onCompilationUnitAfter(CompilationUnit compilationUnit) {
        UnresolvedTypeCollector unresolvedTypeCollector = new UnresolvedTypeCollector();
        unresolvedTypeCollector.transform(compilationUnit);
    }

    @Override
    public LetLocalDef transformBody(LetLocalDef oldLocalLetDef) {
        LetLocalDef transformBody = super.transformBody(oldLocalLetDef);
        Optional.ofNullable(this.states.get(transformBody.getName())).map(InferStates::of).ifPresent(transformBody::setInferState);
        return transformBody;
    }

    @Override
    public Parameter transformBody(Parameter parameter) {
        SimpleName paramName = parameter.getName();
        if (!this.states.containsKey(paramName)) {
            System.err.println("!? " + paramName);
            return parameter;
        }
        Class<?> paramClazz = this.states.get(paramName);
        return new Parameter(parameter.getName(), new FullyQualifiedTypeName(paramClazz));
    }

}
