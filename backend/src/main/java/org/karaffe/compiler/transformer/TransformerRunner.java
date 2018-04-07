package org.karaffe.compiler.transformer;

import org.karaffe.compiler.ast.CompilationUnit;
import org.karaffe.compiler.transformer.api.BaseTransformer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TransformerRunner {

    private final List<BaseTransformer> transformers = new ArrayList<>();

    public TransformerRunner() {
        this.transformers.add(new KNormalizer());
        this.transformers.add(new AlphaEquivalenceTransformer());
        this.transformers.add(new OperatorNameRemapper());
        this.transformers.add(new DefaultImportTransformer());
    }

    public TransformerRunner(Set<? extends BaseTransformer> transformers) {
        this.transformers.addAll(transformers);
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
