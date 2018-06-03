package org.karaffe.compiler.base.tree;

import org.karaffe.compiler.base.tree.def.Def;
import org.karaffe.compiler.base.tree.expr.Apply;
import org.karaffe.compiler.base.tree.expr.Atom;
import org.karaffe.compiler.base.tree.expr.Block;
import org.karaffe.compiler.base.tree.expr.Operator;
import org.karaffe.compiler.base.tree.expr.Select;
import org.karaffe.compiler.base.tree.expr.Tuple;
import org.karaffe.compiler.base.tree.modifier.Modifier;
import org.karaffe.compiler.base.tree.term.Name;
import org.karaffe.compiler.base.tree.type.Type;

public interface TreeVisitor<R, P> {
    R visit(Def def, P p);

    R visit(Name simpleName, P p);

    R visit(Modifier modifier, P p);

    R visit(Type type, P p);

    R visitCompileUnit(Tree.CompilationUnit tree, P p);

    R visitTemplate(Tree.Template template, P p);

    R visitApply(Apply apply, P p);

    R visitAtom(Atom atom, P p);

    R visitBlock(Block block, P p);

    R visitSelect(Select select, P p);

    R visitOperator(Operator operator, P p);

    R visitTuple(Tuple tuple, P p);
}
