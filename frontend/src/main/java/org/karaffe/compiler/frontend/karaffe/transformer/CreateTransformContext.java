package org.karaffe.compiler.frontend.karaffe.transformer;

import org.karaffe.compiler.base.Errors;
import org.karaffe.compiler.frontend.karaffe.ast.names.TypeName;

public class CreateTransformContext extends AbstractTransformer {

    public CreateTransformContext() {
        super("create-context");
    }

    @Override
    public void onTypeNameAfter(TypeName typeName) {
        if (!typeName.isFullyQualified()) {
            Errors.symbolNotFound(typeName.toString(), typeName.getPosition());
        }
    }
}
