package org.karaffe.compiler.base.tree;

import org.karaffe.compiler.base.tree.element.ClassDef;
import org.karaffe.compiler.base.tree.element.ImportDef;
import org.karaffe.compiler.base.tree.element.MainMethodDef;
import org.karaffe.compiler.base.tree.element.MethodDef;
import org.karaffe.compiler.base.tree.element.PackageDef;
import org.karaffe.compiler.base.tree.modifiers.Modifier;
import org.karaffe.compiler.base.tree.term.Name;
import org.karaffe.compiler.base.tree.type.Type;

public interface TreeVisitor<R, P> {
    R visit(ClassDef classDef, P p);

    R visit(BinaryExpr binaryExpr, P p);

    R visit(Source source, P p);

    R visit(PackageDef packageDef, P p);

    R visit(ImportDef importDef, P p);

    R visit(MethodDef methodDef, P p);

    R visit(MainMethodDef mainMethodDef, P p);

    R visit(Name simpleName, P p);

    R visit(Modifier modifier, P p);

    R visit(Type type, P p);
}
