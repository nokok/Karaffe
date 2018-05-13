package org.karaffe.compiler.frontend.karaffe.transformer.util;

import org.karaffe.compiler.base.generator.Generator;
import org.karaffe.compiler.base.generator.TypeVarGenerator;
import org.karaffe.compiler.base.types.constraints.TypeConstraint;
import org.karaffe.compiler.base.types.constraints.TypeVar;
import org.karaffe.compiler.base.types.state.InferState;
import org.karaffe.compiler.frontend.karaffe.ast.api.Expression;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Deprecated
public class TypeEnv {

    private final Generator<TypeVar> nameGen = new TypeVarGenerator();
    private final Map<Expression, TypeVar> tyVarMaping = new LinkedHashMap<>();
    private final Map<TypeVar, InferState> state = new LinkedHashMap<>();
    private final List<TypeConstraint> constraints = new ArrayList<>();
    private final Map<Expression, InferState> exprState = new LinkedHashMap<>();

    public void addExpr(Expression expression) {

    }
}
