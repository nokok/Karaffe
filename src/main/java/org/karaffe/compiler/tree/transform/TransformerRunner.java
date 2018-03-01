package org.karaffe.compiler.tree.transform;

import java.util.ArrayList;
import java.util.List;

import org.karaffe.compiler.tree.transform.api.BaseTransformer;
import org.karaffe.compiler.tree.v2.CompilationUnit;

public class TransformerRunner {

    private final List<BaseTransformer> transformers = new ArrayList<>();

    public TransformerRunner() {
        this.transformers.add(new KNormalizer());
        this.transformers.add(new AlphaEquivalenceTransformer());
        this.transformers.add(new NameResolver());
        this.transformers.add(new TypeInferer());
    }

    public CompilationUnit transform(CompilationUnit compilationUnit) {
        return transform(compilationUnit, "");
    }

    public CompilationUnit transform(CompilationUnit compilationUnit, String breakTransformer) {
        CompilationUnit lastResult = compilationUnit;
        for (BaseTransformer transformer : this.transformers) {
            lastResult = transformer.transform(lastResult);
            if (transformer.getTransformerName().equals(breakTransformer)) {
                return lastResult;
            }
        }
        return lastResult;
    }
}
