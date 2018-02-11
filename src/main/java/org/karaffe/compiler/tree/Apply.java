package org.karaffe.compiler.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.karaffe.compiler.context.NormalizeContext;
import org.karaffe.compiler.context.TypeContext;
import org.karaffe.compiler.tree.base.AbstractNode;
import org.karaffe.compiler.tree.base.Node;
import org.karaffe.compiler.types.InferResult;
import org.karaffe.compiler.types.InferResult.ResultType;
import org.karaffe.compiler.types.MayBeApplicable;

public class Apply extends AbstractNode {

    public Apply(final Node target) {
        this(target, new ArrayList<>());
    }

    public Apply(final Node target, final List<Node> args) {
        super(NodeType.APPLY, target);
        args.stream().forEach(this::addChild);
    }

    public Apply(final Node target, final Node... args) {
        this(target, Arrays.asList(args));
    }

    public Optional<List<? extends Node>> findArguments() {
        final List<? extends Node> children = this.getChildren();
        final int childrenSize = children.size();
        if (childrenSize == 1) {
            return Optional.empty();
        }
        return Optional.ofNullable(children.subList(1, childrenSize));
    }

    public Node findTarget() {
        return this.getChildren().get(0);
    }

    @Override
    public NodeList normalize(final NormalizeContext context) {
        final List<Node> nodes = new ArrayList<>();
        final Node originalTarget = this.findTarget();
        Node newTarget;
        if (originalTarget.isName()) {
            newTarget = originalTarget;
        } else {
            final NodeList normalizedName = originalTarget.normalize(context);
            nodes.addAll(normalizedName.flatten());
            newTarget = normalizedName.lastAssignName();
        }
        final Optional<List<? extends Node>> argumentsOpt = this.findArguments();
        if (argumentsOpt.isPresent()) {
            final List<Node> newArgs = new ArrayList<>();
            final List<? extends Node> args = argumentsOpt.get();
            for (final Node arg : args) {
                if (arg.isName()) {
                    newArgs.add(arg);
                } else {
                    final NodeList normalizedArg = arg.normalize(context);
                    nodes.addAll(normalizedArg.flatten());
                    newArgs.add(normalizedArg.lastAssignName());
                }
            }
            final Apply newApply = new Apply(newTarget, newArgs);
            final Name res = context.nextName(nodes);
            final Assign assign = new Assign(res, newApply);
            nodes.add(assign);
            return new NodeList(nodes);
        }
        final Apply newApply = new Apply(newTarget);
        final Name res = context.nextName(nodes);
        final Assign assign = new Assign(res, newApply);
        nodes.add(assign);

        return new NodeList(nodes);
    }

    @Override
    public String vSource() {
        return String.format("%s(%s)", this.findTarget().vSource(), this.findArguments().map(args -> String.join(",", args.stream().map(Node::vSource).collect(Collectors.toList()))).orElse(""));
    }

    @Override
    public Optional<InferResult> tryTypeInference(TypeContext context) {
        Name targetName = (Name) this.findTarget();
        Optional<List<Name>> argsOpt = this.findArguments().map(a -> a.stream().map(Name.class::cast).collect(Collectors.toList()));
        Optional<InferResult> targetTypeOpt = context.getInferredType(targetName);
        Optional<List<InferResult>> argsTypeOpt = argsOpt.map(args -> args.stream().map(context::getInferredType).filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList()));
        if (!targetTypeOpt.isPresent()) {
            return Optional.empty();
        }
        boolean hasAnyArgs = argsOpt.isPresent();
        if (hasAnyArgs) {
            List<Name> args = argsOpt.get();
            boolean hasError = argsTypeOpt.map(argsType -> argsType.size()).filter(iSize -> !iSize.equals(args.size())).isPresent();
            if (hasError) {
                return Optional.of(InferResult.failed());
            }
            List<InferResult> argsType = argsTypeOpt.get();
            InferResult targetType = targetTypeOpt.get();
            if (targetType.getInferResultType() == ResultType.MAY_BE_APPLICABLE) {
                MayBeApplicable applicable = (MayBeApplicable) targetType;
                return Optional.of(InferResult.applying(applicable, argsType));
            }
        }
        return super.tryTypeInference(context);
    }

    // @Override
    // public Optional<InferResult> tryTypeInference(TypeInferenceContext context) {
    // // オーナーの型を特定する
    // Optional<InferResult> inferResultOpt = tryTypeInferenceOwner(context);
    // String typeName =
    // inferResultOpt.filter(InferResult::isComplete).flatMap(InferResult::getType).orElse("");
    //
    // if (typeName.isEmpty()) {
    // // オーナーの型を特定するのに失敗した
    // return Optional.empty();
    // }
    //
    // // 期待する引数リストで型推論に失敗したものがあれば型推論失敗として返却する
    // if (this.hasTypeErrorParameters(context)) {
    // return Optional.empty();
    // }
    // // ここからはすべての引数の型の解決は完了しているものとして扱う
    // // 期待する引数リストを構築する
    // Optional<List<Class<?>>> parameterTypesOpt = findParameterTypes(context);
    // if (!parameterTypesOpt.isPresent()) {
    // // 引数リスト構築失敗
    // return Optional.empty();
    // }
    //
    // List<Class<?>> parameterTypes = parameterTypesOpt.get();
    // // メソッドを取得する。オーバーロードを考慮するのでリストで取得する。
    // String methodName = findApplyMemberName();
    // Optional<Class<?>> clazzOpt = TypeResolver.findClass(typeName);
    // if (!clazzOpt.isPresent()) {
    // // オーナーの型解決失敗
    // return Optional.empty();
    // }
    // Class<?> clazz = clazzOpt.get();
    // List<Method> methods = new MethodResolver(clazz).findMethods(methodName);
    // if (methods.isEmpty()) {
    // // メソッドがそもそもなかった場合
    // return Optional.empty();
    // }
    // Set<List<Class<?>>> parameterLists = methods.stream()
    // .map(Method::getParameters)
    // .<List<Class<?>>>map((Parameter[] params) ->
    // Stream.of(params).map(Parameter::getType).collect(Collectors.toList()))
    // .collect(Collectors.toSet());
    //
    // Optional<List<Class<?>>> resolvedParameterTypesOpt =
    // parameterLists.stream().filter(actualTypes ->
    // actualTypes.equals(parameterTypes)).findFirst();
    // if (!resolvedParameterTypesOpt.isPresent()) {
    // // 合致するメソッドが見つからなかった
    // return Optional.empty();
    // }
    // List<Class<?>> resolvedParameterTypes = resolvedParameterTypesOpt.get();
    // Class<?>[] parameterTypesA = resolvedParameterTypes.toArray(new
    // Class<?>[resolvedParameterTypes.size()]);
    //
    // Optional<Method> methodOpt = methods.stream().filter(method ->
    // method.getParameterTypes().equals(parameterTypesA)).findFirst();
    // return Optional.of(InferResult.of(methodOpt.get().getReturnType().getCanonicalName()));
    // }
    //
    // private String findApplyMemberName() {
    // return "";
    // }
    //
    // private Optional<InferResult> tryTypeInferenceOwner(TypeInferenceContext context) {
    // return context.getInferredType((Name) this.findTarget());
    // }
    //
    // private boolean hasTypeErrorParameters(TypeInferenceContext context) {
    // Optional<List<InferResult>> result = this.findArguments()
    // .map(args -> args
    // .stream()
    // .map(node -> node
    // .tryTypeInference(context)
    // .orElse(InferResult.failed()))
    // .filter(InferResult::isFailed)
    // .map(Failed.class::cast)
    // .collect(Collectors.toList()));
    // return result.map(l -> l.size() > 0).orElse(false);
    // }
    //
    // private Optional<List<Class<?>>> findParameterTypes(TypeInferenceContext context) {
    // Optional<List<? extends Node>> argsOpt = this.findArguments();
    // Optional<List<InferResult>> resultsOpt = argsOpt.map(args -> args.stream()
    // .<InferResult>map(arg -> arg.tryTypeInference(context)
    // .orElse(InferResult.failed()))
    // .collect(Collectors.toList()));
    // Optional<List<Class<?>>> map = resultsOpt.map(results -> results.stream()
    // .map(InferResult::getType)
    // .map(fqcnOpt -> fqcnOpt.orElse(""))
    // .filter(TypeResolver::isValidFQCN)
    // .<Optional<Class<?>>>map(TypeResolver::findClass)
    // .filter(Optional::isPresent)
    // .<Class<?>>map(Optional::get)
    // .collect(Collectors.toList()));
    // return map;
    // }

}
