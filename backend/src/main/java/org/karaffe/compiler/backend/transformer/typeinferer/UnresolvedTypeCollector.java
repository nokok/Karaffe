package org.karaffe.compiler.backend.transformer.typeinferer;

import org.karaffe.compiler.backend.transformer.AbstractTransformer;
import org.karaffe.compiler.ast.names.TypeName;

public class UnresolvedTypeCollector extends AbstractTransformer {

    public UnresolvedTypeCollector() {
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
