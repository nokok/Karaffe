package org.karaffe.compiler.base.tree;

import org.karaffe.compiler.base.tree.def.AssignmentDef;
import org.karaffe.compiler.base.tree.def.ClassDef;
import org.karaffe.compiler.base.tree.def.LetDef;
import org.karaffe.compiler.base.tree.def.MethodDef;
import org.karaffe.compiler.base.tree.def.OnDemandImport;
import org.karaffe.compiler.base.tree.def.PackageDef;
import org.karaffe.compiler.base.tree.def.SimpleImport;
import org.karaffe.compiler.base.tree.expr.Apply;
import org.karaffe.compiler.base.tree.expr.Atom;
import org.karaffe.compiler.base.tree.expr.Binding;
import org.karaffe.compiler.base.tree.expr.Block;
import org.karaffe.compiler.base.tree.expr.Cast;
import org.karaffe.compiler.base.tree.expr.IfExpr;
import org.karaffe.compiler.base.tree.expr.Operator;
import org.karaffe.compiler.base.tree.expr.Tuple;
import org.karaffe.compiler.base.tree.expr.WhileExpr;
import org.karaffe.compiler.base.tree.modifier.Modifier;
import org.karaffe.compiler.base.tree.stmt.ReturnStatement;
import org.karaffe.compiler.base.tree.term.EmptyTree;
import org.karaffe.compiler.base.tree.term.NameNode;
import org.karaffe.compiler.base.tree.term.NestedPath;
import org.karaffe.compiler.base.tree.term.Path;
import org.karaffe.compiler.base.tree.term.SimplePath;

public interface TreeVisitor<R, P> {

    R visit(LetDef simpleDef, P p);

    R visit(AssignmentDef simpleDef, P p);

    R visit(ClassDef def, P p);

    R visit(SimpleImport def, P p);

    R visit(OnDemandImport onDemandImport, P p);

    R visit(MethodDef def, P p);

    R visit(PackageDef def, P p);

    R visitStaticMod(Modifier modifier, P p);

    R visitPublicMod(Modifier modifier, P p);

    R visitSyntheticMod(Modifier simpleModifier, P p);

    R visit(Tree.CompilationUnit tree, P p);

    R visit(Tree.Template template, P p);

    R visit(Apply apply, P p);

    R visit(Atom atom, P p);

    R visit(Block block, P p);

    R visit(Tuple tuple, P p);

    R visit(IfExpr ifExpr, P p);

    R visit(WhileExpr whileExpr, P p);

    R visit(EmptyTree emptyTree, P p);

    R visit(Cast cast, P p);

    R visit(Binding binding, P p);

    R visit(ReturnStatement returnStatement, P p);

    R visit(NameNode nameNode, P p);

    default Path visitOperator(Operator operator, P p) {
        return operator;
    }

    default Path visitModuleName(Path path, P p) {
        return path;
    }

    default Path visitPackageName(Path path, P p) {
        return path;
    }

    default Path visitEmptyName(Path emptyPath, P p) {
        return emptyPath;
    }

    default Path visitTypeName(Path path, P p) {
        return path;
    }

    default Path visitVarName(Path path, P p) {
        return path;
    }

    default Path visitThisName(Path path, P p) {
        return path;
    }

    default Path visitNestedName(NestedPath path, P p) {
        return path;
    }

    default Path visitSuperName(SimplePath path, P p) {
        return path;
    }

    default Path visitVoidType(Path path, P p) {
        return path;
    }
}
