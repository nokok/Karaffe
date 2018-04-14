package org.karaffe.compiler.frontend.karaffe.transformer;

import org.karaffe.compiler.ast.names.TypeName;

public class TypeChecker extends AbstractTransformer {
    public TypeChecker() {
        super("type-checker");
    }

    @Override
    public void onTypeNameBefore(TypeName typeName) {
        System.out.println(typeName);
        if (!typeName.isFullyQualified()) {
            System.out.println("Symbol not found. : " + typeName);
        }
    }
}
