package org.karaffe.compiler.transformer.namer;

import org.karaffe.compiler.ast.names.TypeName;
import org.karaffe.compiler.transformer.AbstractTransformer;

public class UnresolvedSymbolCollector extends AbstractTransformer {

    public UnresolvedSymbolCollector() {
        super("unresolved-type-collector");
    }

    @Override
    public void onTypeNameBefore(TypeName typeName) {
        System.out.println(typeName);
        if (!typeName.isFullyQualified()) {
            System.out.println("Symbol not found. : " + typeName);
        }
    }

}
