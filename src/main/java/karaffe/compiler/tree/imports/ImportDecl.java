/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree.imports;

import karaffe.compiler.tree.AST;
import karaffe.compiler.tree.AbstractNode;
import karaffe.compiler.visitor.Visitor;

public class ImportDecl extends AbstractNode {

    private final AST importDecl;

    public ImportDecl(Object i) {
        this.importDecl = (AST) i;
        addChildren(importDecl);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.importDecl(this);
    }

    @Override
    public String toString() {
        return "(ImportDecl:" + importDecl + ")";
    }

}
