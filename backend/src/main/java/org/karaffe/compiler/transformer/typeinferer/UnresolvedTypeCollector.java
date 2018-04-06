package org.karaffe.compiler.transformer.typeinferer;

import org.karaffe.compiler.ast.names.TypeName;
import org.karaffe.compiler.transformer.AbstractTransformer;

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
