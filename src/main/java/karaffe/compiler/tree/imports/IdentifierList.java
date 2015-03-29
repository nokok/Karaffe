/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree.imports;

import karaffe.compiler.tree.AST;
import karaffe.compiler.tree.AbstractNode;
import karaffe.compiler.visitor.Visitor;

public class IdentifierList extends AbstractNode {

    private final AST id;
    private final AST list;

    public IdentifierList(Object id) {
        this(id, null);
    }

    public IdentifierList(Object id, Object list) {
        this.id = (AST) id;
        this.list = (AST) list;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.identifierList(this);
    }

    @Override
    public String toString() {
        return "(IdentifierList:" + id + "," + list + ")";
    }

}
