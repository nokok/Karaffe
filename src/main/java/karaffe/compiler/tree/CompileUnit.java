/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree;

import karaffe.compiler.visitor.Visitor;

public class CompileUnit extends AbstractNode {

    private final AST packageDecl;
    private final AST importDecl;

    public CompileUnit(Object p, Object i) {
        this.packageDecl = (AST) p;
        this.importDecl = (AST) i;
        if (packageDecl != null) {
            children.add(packageDecl);
        }
        if (importDecl != null) {
            children.add(importDecl);
        }
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.compileUnit(this);
    }

    @Override
    public String toString() {
        return "(CompileUnit:" + packageDecl + importDecl + ")";
    }

}
