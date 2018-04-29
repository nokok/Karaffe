package org.karaffe.compiler.frontend.karaffe.transformer;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class TransformerBuilder {

    private final Map<AbstractTransformer, Set<AbstractTransformer>> dependencyMap = new HashMap<>();

    private static final AbstractTransformer contextCreator = new CreateTransformContext();
    private static final AbstractTransformer defaultImport = new DefaultImportTransformer();
    private static final AbstractTransformer nameResolver = new NameResolver();
    private static final AbstractTransformer cleanUp = new CleanupTransformer();
    private static final AbstractTransformer typeInfer = new TypeInferer();
    private static final AbstractTransformer typeChecker = new TypeChecker();
    private static final AbstractTransformer opNameRemap = new OperatorNameRemapper();
    private static final AbstractTransformer kNormal = new KNormalizer();
    private static final AbstractTransformer alpha = new AlphaEquivalenceTransformer();
    private static final AbstractTransformer terminal = new TerminalTransformer();

    public TransformerBuilder() {
        this.dependencyMap.put(defaultImport,Collections.emptySet());
        this.dependencyMap.put(nameResolver, Collections.singleton(defaultImport));
        this.dependencyMap.put(cleanUp, Collections.singleton(nameResolver));
        this.dependencyMap.put(contextCreator, Collections.singleton(cleanUp));
        this.dependencyMap.put(typeInfer, Collections.singleton(contextCreator));
        this.dependencyMap.put(typeChecker, Collections.singleton(typeInfer));
        this.dependencyMap.put(opNameRemap, Collections.singleton(typeChecker));
        this.dependencyMap.put(kNormal, Collections.singleton(opNameRemap));
        this.dependencyMap.put(alpha, Collections.singleton(kNormal));
        this.dependencyMap.put(terminal, Collections.singleton(alpha));
    }

    public Set<AbstractTransformer> getTransformers() {
        return buildTransformers(terminal.getTransformerName());
    }

    public Set<AbstractTransformer> buildTransformers(String transformerName) {
        AbstractTransformer startTransformer = this.dependencyMap
                .keySet().stream()
                .filter(transformer -> transformer.getTransformerName().equals(transformerName))
                .findAny()
                .orElseThrow(IllegalStateException::new);
        return buildTransformers(startTransformer);
    }

    public Set<AbstractTransformer> buildTransformers(AbstractTransformer transformer) {
        Set<AbstractTransformer> depends = this.dependencyMap.get(transformer);
        Set<AbstractTransformer> ret = new LinkedHashSet<>();
        for (AbstractTransformer dep : depends) {
            ret.addAll(buildTransformers(dep));
        }
        ret.add(transformer);
        return ret;
    }
}
