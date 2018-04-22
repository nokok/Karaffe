package org.karaffe.compiler.frontend.karaffe.transformer;

import org.karaffe.compiler.base.util.Platform;
import org.karaffe.compiler.frontend.karaffe.ast.api.Expression;
import org.karaffe.compiler.frontend.karaffe.ast.names.TypeName;
import org.karaffe.compiler.frontend.karaffe.transformer.AbstractTransformer;
import org.karaffe.compiler.frontend.karaffe.transformer.util.TransformerContext;

public class CreateTransformContext extends AbstractTransformer {

    public CreateTransformContext() {
        super("create-context");
    }

    @Override
    public void onTypeNameAfter(TypeName typeName) {
        if (!typeName.isFullyQualified()) {
            Platform.stdErr("Symbol not found. : " + typeName);
        }
    }
}
