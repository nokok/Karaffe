/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree.imports;

import karaffe.compiler.tree.AST;
import karaffe.compiler.tree.AbstractNode;
import karaffe.compiler.visitor.Visitor;

public class SimpleImport extends AbstractNode {

    private final AST name;

    public SimpleImport(Object n) {
        this.name = (AST) n;
        addChildren(name);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.simpleImportDecl(this);
    }

    @Override
    public String toString() {
        return "(SimpleImport:" + name + ")";
    }

}
