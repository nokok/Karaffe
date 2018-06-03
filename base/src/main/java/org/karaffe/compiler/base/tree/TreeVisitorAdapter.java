package org.karaffe.compiler.base.tree;

import org.karaffe.compiler.base.tree.def.Def;
import org.karaffe.compiler.base.tree.expr.Apply;
import org.karaffe.compiler.base.tree.expr.Atom;
import org.karaffe.compiler.base.tree.expr.Block;
import org.karaffe.compiler.base.tree.expr.Operator;
import org.karaffe.compiler.base.tree.expr.Select;
import org.karaffe.compiler.base.tree.modifier.Modifier;
import org.karaffe.compiler.base.tree.term.Name;
import org.karaffe.compiler.base.tree.type.Type;

public abstract class TreeVisitorAdapter<R, P> implements TreeVisitor<R, P> {
    @Override
    public R visit(Def def, P p) {
        return null;
    }

    @Override
    public R visit(Name simpleName, P p) {
        return null;
    }

    @Override
    public R visit(Modifier modifier, P p) {
        return null;
    }

    @Override
    public R visit(Type type, P p) {
        return null;
    }

    @Override
    public R visitCompileUnit(Tree.CompilationUnit tree, P p) {
        return null;
    }

    @Override
    public R visitTemplate(Tree.Template template, P p) {
        return null;
    }

    @Override
    public R visitApply(Apply apply, P p) {
        return null;
    }

    @Override
    public R visitAtom(Atom atom, P p) {
        return null;
    }

    @Override
    public R visitBlock(Block block, P p) {
        return null;
    }

    @Override
    public R visitSelect(Select select, P p) {
        return null;
    }

    @Override
    public R visitOperator(Operator operator, P p) {
        return null;
    }
}
