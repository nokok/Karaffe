package org.karaffe.compiler.base.tree;

import org.karaffe.compiler.base.tree.def.Def;
import org.karaffe.compiler.base.tree.expr.Expr;
import org.karaffe.compiler.base.tree.modifier.Modifier;
import org.karaffe.compiler.base.tree.term.Name;
import org.karaffe.compiler.base.tree.type.Type;

public interface TreeVisitor<R, P> {
    R visit(Def def, P p);

    R visit(Expr binaryExpr, P p);

    R visit(Name simpleName, P p);

    R visit(Modifier modifier, P p);

    R visit(Type type, P p);

    R visitCompileUnit(Tree.CompilationUnit tree, P p);

    R visitTemplate(Tree.Template template, P p);

}
