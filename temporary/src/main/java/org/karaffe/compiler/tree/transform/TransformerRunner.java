package org.karaffe.compiler.tree.transform;

import java.util.ArrayList;
import java.util.List;

import org.karaffe.compiler.tree.transform.api.BaseTransformer;
import org.karaffe.compiler.tree.transform.namer.NameNormalizeTransformer;
import org.karaffe.compiler.tree.transform.namer.NameRemapper;
import org.karaffe.compiler.tree.transform.typeinferer.TypeInferer;
import org.karaffe.compiler.tree.v2.CompilationUnit;

public class TransformerRunner {

    private final List<BaseTransformer> transformers = new ArrayList<>();

    public TransformerRunner() {
        this.transformers.add(new NameNormalizeTransformer());
        this.transformers.add(new NameRemapper());
        this.transformers.add(new TypeInferer());
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
