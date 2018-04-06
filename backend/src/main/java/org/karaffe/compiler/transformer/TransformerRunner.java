package org.karaffe.compiler.transformer;

import org.karaffe.compiler.ast.CompilationUnit;
import org.karaffe.compiler.transformer.api.BaseTransformer;
import org.karaffe.compiler.transformer.namer.NameNormalizeTransformer;
import org.karaffe.compiler.transformer.namer.NameRemapper;

import java.util.ArrayList;
import java.util.List;

public class TransformerRunner {

    private final List<BaseTransformer> transformers = new ArrayList<>();

    public TransformerRunner() {
        this.transformers.add(new NameNormalizeTransformer());
        this.transformers.add(new NameRemapper());
    }

    public TransformerRunner(List<? extends BaseTransformer> transformers) {
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
