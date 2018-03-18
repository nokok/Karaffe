package org.karaffe.compiler.tree.transform.typeinferer;

import java.lang.reflect.Method;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import org.karaffe.compiler.base.types.InferStates;
import org.karaffe.compiler.backend.jvm.resolvers.MethodResolver;
import org.karaffe.compiler.backend.jvm.resolvers.TypeResolver;
import org.karaffe.compiler.tree.transform.AbstractTransformer;
import org.karaffe.compiler.ast.Parameter;
import org.karaffe.compiler.ast.api.Expression;
import org.karaffe.compiler.ast.api.Statement;
import org.karaffe.compiler.ast.api.Tree;
import org.karaffe.compiler.ast.expressions.Apply;
import org.karaffe.compiler.ast.expressions.Block;
import org.karaffe.compiler.ast.expressions.ExpressionName;
import org.karaffe.compiler.ast.expressions.IntLiteral;
import org.karaffe.compiler.ast.expressions.NewInstance;
import org.karaffe.compiler.ast.expressions.StaticApply;
import org.karaffe.compiler.ast.names.FullyQualifiedTypeName;
import org.karaffe.compiler.ast.names.SimpleName;
import org.karaffe.compiler.ast.names.TypeName;
import org.karaffe.compiler.ast.statements.LetLocalDef;
import org.karaffe.compiler.ast.statements.MethodDef;
import org.karaffe.compiler.backend.jvm.resolvers.types.JavaInferStates;
import org.karaffe.compiler.types.v2.TypeConstraints;
import org.karaffe.compiler.types.v2.constraints.ConstraintType;
import org.karaffe.compiler.types.v2.constraints.HasMember;
import org.karaffe.compiler.types.v2.constraints.NeedEquals;
import org.karaffe.compiler.types.v2.constraints.TypeConstraint;
import org.karaffe.compiler.base.types.InferState;
import org.karaffe.compiler.base.types.InferStateType;
import org.karaffe.compiler.backend.jvm.resolvers.types.state.JavaTypeResolved;

import karaffe.core.Int;

public class TypeEnvironmentBuilder extends AbstractTransformer {

    public TypeEnvironmentBuilder() {
        super("type-constraint-builder");
    }

    private final List<TypeConstraint> constraints = new ArrayList<>();
    private final Map<SimpleName, InferState> states = new LinkedHashMap<>();

    @Override
    public void onBlockBefore(Block block) {
        block.asExprName().ifPresent(exprName -> transform(exprName));
    }

    @Override
    public void onMethodBefore(MethodDef methodDef) {
        TypeName returnTypeName = methodDef.getReturnTypeName();
        Statement lastStatement = methodDef.getBody().get(methodDef.getBody().size() - 1);

        switch (lastStatement.getStatementType()) {
        case EXPRESSION:
            Expression expression = (Expression) lastStatement;
            expression.asExprName().map((Expression exprName) -> TypeConstraints.needEquals(exprName, returnTypeName)).ifPresent(this.constraints::add);
            break;
        default:
        }
        updateEnvironment();
    }

    @Override
    public void onParameterBefore(Parameter parameter) {
        if (!parameter.getType().isFullyQualified()) {
            return;
        }
        FullyQualifiedTypeName name = (FullyQualifiedTypeName) parameter.getType();
        TypeResolver.findClass(name.getFullName()).map(JavaInferStates::of).ifPresent(s -> this.states.put(parameter.getName(), s));
    }

    @Override
    public void onIntLiteralBefore(IntLiteral intLiteral) {
        TypeResolver.findAllCompatibleClasses(Int.class).map(JavaInferStates::of).ifPresent(state -> {
            this.constraints.add(TypeConstraints.needEquals(intLiteral, new FullyQualifiedTypeName(Int.class)));
        });
    }

    @Override
    public void onLetLocalDefBefore(LetLocalDef letLocalDef) {
        if (letLocalDef.hasTypeName()) {
            TypeName typeName = letLocalDef.getTypeName().get();
            // (型チェックが未完了なので)型が決定したわけではないので型宣言があっても制約として扱う
            this.constraints.add(TypeConstraints.needEquals(letLocalDef.getName(), typeName));
        } else {
            this.states.put(letLocalDef.getName(), InferStates.noHint());
        }

        letLocalDef.getInitializer().ifPresent(initializer -> {
            this.constraints.add(TypeConstraints.needEquals(letLocalDef.getName(), initializer));
            switch (initializer.getExpressionType()) {
            case INT_LITERAL:
                TypeResolver
                        .findAllCompatibleClasses(Int.class)
                        .map(JavaInferStates::of)
                        .map(state -> this.states.put(letLocalDef.getName(), state))
                        .orElseGet(() -> this.states.put(letLocalDef.getName(), InferStates.fail()));
                break;
            case STATIC_APPLY:
                StaticApply staticApply = (StaticApply) initializer;
                TypeName typeName = staticApply.getTypeName();
                SimpleName methodName = staticApply.getMethodName();
                if (typeName.isFullyQualified()) {
                    FullyQualifiedTypeName fqtn = (FullyQualifiedTypeName) typeName;
                    String fqcn = fqtn.getFullName();
                    int paramSize = staticApply.getArgs().size();
                    List<Method> methods = TypeResolver.findClass(fqcn).map(MethodResolver::new).map(methodResolver -> methodResolver.findMethodsByMethodNameWithParameterSize(methodName, paramSize)).orElseGet(ArrayList::new);
                    if (methods.isEmpty()) {
                        throw new RuntimeException("Type or Method not found : " + fqcn + "#" + methodName);
                    }
                    if (methods.size() == 1) {
                        Method method = methods.get(0);
                        Class<?> returnType = method.getReturnType();
                        this.states.put(letLocalDef.getName(), JavaInferStates.of(returnType));
                    } else {
                        throw new RuntimeException("Method overload is not supported.");
                    }
                }
                break;
            case APPLY:
                Apply apply = (Apply) initializer;
                Optional<InferState> nullableInferState = Optional.ofNullable(this.states.get(apply.getExpression()));
                nullableInferState.filter(i -> i.getInferStateType().equals(InferStateType.RESOLVED)).map(JavaTypeResolved.class::cast).ifPresent(resolved -> {
                    Class<?> clazz = resolved.getSuitableType();
                    MethodResolver methodResolver = new MethodResolver(clazz);
                    List<Method> methods = methodResolver.findMethodsByMethodName(apply.getMethodName());
                    if (methods.size() == 1) {
                        Method method = methods.get(0);
                        Class<?>[] parameterTypes = method.getParameterTypes();
                        List<? extends Expression> args = apply.getArgs();
                        if (parameterTypes.length != args.size()) {
                            throw new RuntimeException("Invalid Method args count");
                        }
                        this.states.put(letLocalDef.getName(), JavaInferStates.of(method.getReturnType()));
                    }
                });
                break;
            case BLOCK:
                Block block = (Block) initializer;
                block.asExprName().ifPresent(exprName -> this.constraints.add(TypeConstraints.needEquals(letLocalDef.getName(), (SimpleName) exprName)));
                break;
            case NAME:
                ExpressionName name = (ExpressionName) initializer;
                Optional.ofNullable(this.states.get(name))
                        .filter(state -> state.getInferStateType().equals(InferStateType.RESOLVED))
                        .map(JavaTypeResolved.class::cast)
                        .ifPresent(resolved -> {
                            this.states.put(letLocalDef.getName(), resolved);
                        });
                break;
            case NEW_INSTANCE:
                NewInstance newInstance = (NewInstance) initializer;
                if (newInstance.getTypeName().isFullyQualified()) {
                    String fqcn = ((FullyQualifiedTypeName) newInstance.getTypeName()).getFullName();
                    TypeResolver.findClass(fqcn).ifPresent(clazz -> {
                        this.states.put(letLocalDef.getName(), JavaInferStates.of(clazz));
                    });
                }
                break;
            default:
                throw new UnsupportedOperationException(initializer.getExpressionType().name());
            }

        });
        updateEnvironment();
    }

    @Override
    public void onApplyBefore(Apply apply) {
        this.constraints.add(TypeConstraints.hasMember(apply.getExpression(), apply.getMethodName()));
        updateEnvironment();
    }

    public List<? extends TypeConstraint> getTypeConstraints() {
        return new ArrayList<>(this.constraints);
    }

    public Map<? extends SimpleName, ? extends InferState> getInferState() {
        return new HashMap<>(this.states);
    }

    private void updateEnvironment() {
        int lastCount = 0;
        int currentCount = updateEnvironment1();
        while (lastCount != currentCount) {
            lastCount = currentCount;
            currentCount = updateEnvironment1();
        }
    }

    private int updateEnvironment1() {
        int modifyCount = 0;
        modifyCount += updateNeedEquals();
        modifyCount += updateHasMember();
        return modifyCount;
    }

    private int updateNeedEquals() {
        int modifyCount = 0;
        List<NeedEquals> needEquals = this.constraints
                .stream()
                .filter(c -> c.getConstraintType().equals(ConstraintType.NEED_EQUALS))
                .map(NeedEquals.class::cast)
                .collect(Collectors.toList());
        Map<SimpleName, JavaTypeResolved> resolvedNames = this.states
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().getInferStateType().equals(InferStateType.RESOLVED))
                .map(entry -> new AbstractMap.SimpleEntry<>(entry.getKey(), (JavaTypeResolved) entry.getValue()))
                .collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));

        for (Map.Entry<SimpleName, JavaTypeResolved> entry : resolvedNames.entrySet()) {
            SimpleName resolvedName = entry.getKey();
            JavaTypeResolved resolvedType = entry.getValue();
            List<Tree> lefts = needEquals
                    .stream()
                    .filter(eq -> eq.getLeftTree().equals(resolvedName))
                    .map(NeedEquals::getRightTree) // resolved -> unresolved
                    .collect(Collectors.toList());
            List<Tree> rights = needEquals
                    .stream()
                    .filter(eq -> eq.getRightTree().equals(resolvedName))
                    .map(NeedEquals::getLeftTree) // unresolved <- resolved
                    .collect(Collectors.toList());
            List<Entry<SimpleName, InferState>> updating = this.states
                    .entrySet()
                    .stream()
                    .filter(s -> lefts.contains(s.getKey()) || rights.contains(s.getKey()))
                    .filter(s -> !s.getValue().getInferStateType().equals(InferStateType.RESOLVED))
                    .collect(Collectors.toList());
            if (updating.isEmpty()) {
                continue;
            }

            List<SimpleName> updatingNames = updating.stream().map(Entry::getKey).collect(Collectors.toList());

            updatingNames.forEach(name -> this.states.compute(name, (key, value) -> resolvedType));
            modifyCount += updatingNames.size();
        }
        return modifyCount;
    }

    private int updateHasMember() {
        int modifyCount = 0;
        List<HasMember> hasMembers = this.constraints
                .stream()
                .filter(c -> c.getConstraintType().equals(ConstraintType.HAS_MEMBER))
                .map(HasMember.class::cast)
                .collect(Collectors.toList());

        return modifyCount;
    }
}
