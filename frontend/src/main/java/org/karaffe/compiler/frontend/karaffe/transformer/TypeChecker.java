package org.karaffe.compiler.frontend.karaffe.transformer;

import org.karaffe.compiler.frontend.karaffe.ast.names.TypeName;

public class TypeChecker extends AbstractTransformer {
    public TypeChecker() {
        super("type-checker");
    }

    @Override
    public void onTypeNameBefore(TypeName typeName) {
        if (!typeName.isFullyQualified()) {
            // TODO: Report Error
        }
    }
}
