package org.karaffe.compiler.tree.transform.typeinferer;

import org.karaffe.compiler.tree.transform.AbstractTransformer;
import org.karaffe.compiler.tree.v2.names.TypeName;

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
