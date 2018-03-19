package org.karaffe.compiler.backend.transformer.namer;

import java.util.ArrayList;
import java.util.List;

import org.karaffe.compiler.backend.transformer.AbstractTransformer;
import org.karaffe.compiler.ast.CompilationUnit;

public class NameRemapper extends AbstractTransformer {

    private final List<AbstractTransformer> transformers = new ArrayList<>();

    public NameRemapper() {
        super("name-remapper");
        this.transformers.add(new OperatorNameRemapper());
        this.transformers.add(new DefaultImportTransformer());
        this.transformers.add(new ClassNameResolver());
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
