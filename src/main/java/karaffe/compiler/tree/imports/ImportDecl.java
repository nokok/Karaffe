/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree.imports;

import karaffe.compiler.tree.AST;
import karaffe.compiler.tree.AbstractNode;
import karaffe.compiler.visitor.Visitor;

public class ImportDecl extends AbstractNode {

    private final AST importDecl;

    public ImportDecl(Object importDecl) {
        this.importDecl = (AST) importDecl;
        children.add(this.importDecl);
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
