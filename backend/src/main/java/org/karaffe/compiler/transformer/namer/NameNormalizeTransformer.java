package org.karaffe.compiler.transformer.namer;

import org.karaffe.compiler.ast.CompilationUnit;
import org.karaffe.compiler.transformer.AbstractTransformer;

import java.util.ArrayList;
import java.util.List;

public class NameNormalizeTransformer extends AbstractTransformer {

    private final List<AbstractTransformer> transformers = new ArrayList<>();

    public NameNormalizeTransformer() {
        super("name-transformer");
        this.transformers.add(new KNormalizer());
        this.transformers.add(new AlphaEquivalenceTransformer());
    }

    @Override
    public CompilationUnit transform(CompilationUnit oldCompilationUnit) {
        CompilationUnit last = oldCompilationUnit;
        for (AbstractTransformer transformer : this.transformers) {
            last = transformer.transform(last);
        }
        return last;
    }

}
