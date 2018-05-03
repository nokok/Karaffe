package org.karaffe.compiler.frontend.karaffe.transformer;

import org.karaffe.compiler.frontend.karaffe.ast.expressions.Apply;
import org.karaffe.compiler.frontend.karaffe.ast.expressions.StaticApply;
import org.karaffe.compiler.frontend.karaffe.ast.names.SimpleName;

import java.util.stream.Collectors;

public class OperatorNameRemapper extends AbstractTransformer {

    public OperatorNameRemapper() {
        super("opname-remapper");
    }

    @Override
    public Apply transformBody(Apply oldApply) {
        Apply newApply = new Apply(oldApply.getPosition(), transform(oldApply.getExpression()), new SimpleName(rename(oldApply.getMethodName().toString())), oldApply.getArgs().stream().map(this::transform).collect(Collectors.toList()));
        return newApply;
    }

    @Override
    public StaticApply transformBody(StaticApply staticApply) {
        return new StaticApply(staticApply.getPosition(), staticApply.getTypeName(), new SimpleName(rename(staticApply.getMethodName().toString())), staticApply.getArgs().stream().map(this::transform).collect(Collectors.toList()));
    }

    private String rename(String methodName) {
        String ret = methodName;
        ret = ret.replaceAll("\\Q+\\E", "plus");
        ret = ret.replaceAll("\\Q-\\E", "minus");
        ret = ret.replaceAll("\\Q*\\E", "mul");
        ret = ret.replaceAll("\\Q/\\E", "div");
        return ret;
    }
}
