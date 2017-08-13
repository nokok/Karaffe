package org.karaffe.compiler.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.karaffe.compiler.tree.base.NodeD;

public class CompileUnit implements NodeD {
    private final PackageDecl packageDecl;
    private final List<ClassDecl> classDecls;

    public CompileUnit(final PackageDecl packageDecl, final List<ClassDecl> classes) {
        this.packageDecl = packageDecl;
        this.classDecls = classes;
    }

    public Optional<NodeD> packageDecl() {
        return Optional.ofNullable(this.packageDecl);
    }

    public List<ClassDecl> classDecls() {
        return new ArrayList<>(this.classDecls);
    }

    @Override
    public String toString() {
        return String.format("(CompileUnit %s %s)", this.packageDecl, this.classDecls);
    }

}
