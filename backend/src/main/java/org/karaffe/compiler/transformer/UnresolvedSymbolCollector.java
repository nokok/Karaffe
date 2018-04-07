package org.karaffe.compiler.transformer;

import org.karaffe.compiler.ast.names.TypeName;

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
