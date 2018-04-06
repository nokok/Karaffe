package org.karaffe.compiler.transformer;

import org.karaffe.compiler.ast.api.Expression;
import org.karaffe.compiler.transformer.util.TransformerContext;

public class CreateTransformContext extends AbstractTransformer {

    private final TransformerContext context = TransformerContext.CONTEXT;

    public CreateTransformContext() {
        super("create-context");
    }

    @Override
    public void onExpressionBefore(Expression expression) {
        context.addExprState(expression);
    }
}
