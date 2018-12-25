package org.karaffe.compiler.typechecker;

import karaffe.core.Int;
import karaffe.core.Unit;
import org.karaffe.compiler.tree.Tree;
import org.karaffe.compiler.util.CompilerContext;
import org.karaffe.compiler.util.TypeInfo;

import java.util.Objects;

public class TypeChecker {

    private final CompilerContext context;

    public TypeChecker(CompilerContext context) {
        this.context = Objects.requireNonNull(context);
    }

    public TypeInfo unify(Tree tree) {
        switch (tree.getNodeType()) {
        case IntLiteral:
            return new TypeInfo(Int.class);
        case StringLiteral:
            return new TypeInfo(karaffe.core.String.class);
        case CompilationUnit:
        case DefClass:
        case DefMethod:
        case DefVar:
            return new TypeInfo(Unit.class);
        case Error:
            return TypeInfo.error();
        default:
            throw new IllegalStateException(tree.getNodeType().name());
        }
    }
}
