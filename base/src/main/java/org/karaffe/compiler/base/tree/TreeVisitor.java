package org.karaffe.compiler.base.tree;

public interface TreeVisitor<T> {
    T visit(CompilationUnit compilationUnit);

    T visit(Source source);

    T visit(ClassDef classDef);

    T visit(MethodDef methodDef);

    T visit(PackageDef packageDef);
}
