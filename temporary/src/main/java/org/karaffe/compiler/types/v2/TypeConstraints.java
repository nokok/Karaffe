package org.karaffe.compiler.types.v2;

import org.karaffe.compiler.ast.api.Expression;
import org.karaffe.compiler.ast.api.Tree;
import org.karaffe.compiler.ast.names.FullyQualifiedTypeName;
import org.karaffe.compiler.ast.names.SimpleName;
import org.karaffe.compiler.ast.names.TypeName;
import org.karaffe.compiler.types.v2.constraints.HasMember;
import org.karaffe.compiler.types.v2.constraints.NeedEquals;
import org.karaffe.compiler.types.v2.constraints.TypeConstraint;

public interface TypeConstraints {

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
