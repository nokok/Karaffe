package org.karaffe.compiler.tree.transform.namer;

import java.util.ArrayList;
import java.util.List;

import org.karaffe.compiler.tree.transform.AbstractTransformer;
import org.karaffe.compiler.ast.CompilationUnit;

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
