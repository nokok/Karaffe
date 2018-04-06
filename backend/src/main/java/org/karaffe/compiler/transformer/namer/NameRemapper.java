package org.karaffe.compiler.transformer.namer;

import org.karaffe.compiler.ast.CompilationUnit;
import org.karaffe.compiler.transformer.AbstractTransformer;

import java.util.ArrayList;
import java.util.List;

public class NameRemapper extends AbstractTransformer {

    private final List<AbstractTransformer> transformers = new ArrayList<>();

    public NameRemapper() {
        super("name-remapper");
        this.transformers.add(new OperatorNameRemapper());
        this.transformers.add(new DefaultImportTransformer());
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
