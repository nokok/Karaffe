package org.karaffe.compiler.tree.transform.namer;

import org.karaffe.compiler.tree.transform.AbstractTransformer;
import org.karaffe.compiler.tree.v2.expressions.Apply;
import org.karaffe.compiler.tree.v2.names.SimpleName;

public class OperatorNameRemapper extends AbstractTransformer {

    public OperatorNameRemapper() {
        super("opname-remapper");
    }

    @Override
    public Apply transformBody(Apply oldApply) {
        String methodName = oldApply.getMethodName().toString();
        methodName = methodName.replaceAll("\\Q+\\E", "plus");
        methodName = methodName.replaceAll("\\Q-\\E", "minus");
        methodName = methodName.replaceAll("\\Q*\\E", "mul");
        methodName = methodName.replaceAll("\\Q/\\E", "div");
        Apply newApply = new Apply(oldApply.getPosition(), oldApply.getExpression(), new SimpleName(methodName), oldApply.getArgs());
        return newApply;
    }

}
