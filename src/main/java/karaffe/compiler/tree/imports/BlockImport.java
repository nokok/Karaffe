/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree.imports;

import karaffe.compiler.tree.AST;
import karaffe.compiler.tree.AbstractNode;
import karaffe.compiler.visitor.Visitor;

public class BlockImport extends AbstractNode {

    private final AST body;

    public BlockImport(Object body) {
        this.body = (AST) body;
        children.add(this.body);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.blockImport(this);
    }

    @Override
    public String toString() {
        return "(BlockImport:" + body + ")";
    }
}
