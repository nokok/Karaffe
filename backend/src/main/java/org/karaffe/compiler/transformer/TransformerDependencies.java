package org.karaffe.compiler.transformer;

import org.karaffe.compiler.transformer.namer.AlphaEquivalenceTransformer;
import org.karaffe.compiler.transformer.namer.DefaultImportTransformer;
import org.karaffe.compiler.transformer.namer.KNormalizer;
import org.karaffe.compiler.transformer.namer.OperatorNameRemapper;
import org.karaffe.compiler.transformer.type.TypeChecker;
import org.karaffe.compiler.transformer.type.TypeInferer;
import org.karaffe.compiler.transformer.namer.UnresolvedSymbolCollector;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class TransformerDependencies {
    private final Map<AbstractTransformer, Set<AbstractTransformer>> dependencyMap = new HashMap<>();

    // Collector
    private static final AbstractTransformer typeChecker = new TypeChecker();
    private static final AbstractTransformer typeInfer = new TypeInferer();
    private static final AbstractTransformer unResolvedTypeCollector = new UnresolvedSymbolCollector();

    // Transformer
    private static final AbstractTransformer alpha = new AlphaEquivalenceTransformer();
    private static final AbstractTransformer kNormal = new KNormalizer();
    private static final AbstractTransformer opNameRemap = new OperatorNameRemapper();
    private static final AbstractTransformer defaultImport = new DefaultImportTransformer();
    private static final AbstractTransformer createDefaultCtor = new CreateDefaultConstructorTransformer();

    // Generator(ReadOnly)
    private static final AbstractTransformer contextCreator = new CreateTransformContext();
    private static final AbstractTransformer jvmAsm = new JVMBytecodeGenerator();

    public TransformerDependencies() {
        this.dependencyMap.put(contextCreator, Collections.emptySet());
        this.dependencyMap.put(typeChecker, Collections.singleton(contextCreator));
        this.dependencyMap.put(typeInfer, Collections.singleton(contextCreator));
        this.dependencyMap.put(unResolvedTypeCollector, Collections.singleton(contextCreator));
        this.dependencyMap.put(kNormal, Collections.emptySet());
        this.dependencyMap.put(alpha, Collections.singleton(kNormal));
        this.dependencyMap.put(opNameRemap, Collections.singleton(alpha));
        this.dependencyMap.put(defaultImport, Collections.singleton(opNameRemap));
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
