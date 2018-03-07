package org.karaffe.compiler.types.v2;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.karaffe.compiler.tree.v2.api.Expression;
import org.karaffe.compiler.tree.v2.api.Tree;
import org.karaffe.compiler.tree.v2.names.FullyQualifiedTypeName;
import org.karaffe.compiler.tree.v2.names.SimpleName;
import org.karaffe.compiler.tree.v2.names.TypeName;
import org.karaffe.compiler.types.v2.constraints.HasMember;
import org.karaffe.compiler.types.v2.constraints.NeedEquals;
import org.karaffe.compiler.types.v2.constraints.TypeConstraint;
import org.karaffe.compiler.types.v2.states.Error;
import org.karaffe.compiler.types.v2.states.InferState;
import org.karaffe.compiler.types.v2.states.NoHint;
import org.karaffe.compiler.types.v2.states.Resolved;
import org.karaffe.compiler.types.v2.states.VoidType;

public interface TypeInfers {
    public static InferState of(Class<? extends Object> clazz) {
        return new Resolved(clazz);
    }

    public static InferState of(Class<?>... clazz) {
        return new Resolved(Arrays.asList(clazz));
    }

    public static InferState noHint() {
        return new NoHint();
    }

    public static InferState voidType() {
        return new VoidType();
    }

    public static InferState fail() {
        return new Error();
    }

    public static InferState of(List<String> allCompatibleClasses) {
        return new Resolved(allCompatibleClasses.stream().<Optional<Class<?>>>map(clazzName -> {
            try {
                return Optional.ofNullable(Class.forName(clazzName));
            } catch (ClassNotFoundException e) {
                return Optional.empty();
            }
        }).map(Optional::get).collect(Collectors.toList()));
    }

    public static TypeConstraint needEquals(SimpleName name1, SimpleName name2) {
        return new NeedEquals(name1, name2);
    }

    public static TypeConstraint needEquals(SimpleName name, Expression expr) {
        return new NeedEquals(name, expr);
    }

    public static TypeConstraint needEquals(Expression expression, TypeName typeName) {
        return new NeedEquals(expression, typeName);
    }

    public static TypeConstraint needEquals(SimpleName name, TypeName typeName) {
        return new NeedEquals(name, typeName);
    }

    public static TypeConstraint needEquals(Expression expression, FullyQualifiedTypeName fullyQualifiedTypeName) {
        return new NeedEquals(expression, fullyQualifiedTypeName);
    }

    public static TypeConstraint hasMember(Tree owner, SimpleName expectedMemberName) {
        return new HasMember(owner, expectedMemberName.toString());
    }

}
