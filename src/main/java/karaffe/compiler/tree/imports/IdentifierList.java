/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree.imports;

import java.util.Optional;
import karaffe.compiler.tree.AST;
import karaffe.compiler.tree.AbstractNode;
import karaffe.compiler.visitor.Visitor;

public class IdentifierList extends AbstractNode {

    private final AST id;
    private final Optional<AST> list;

    public IdentifierList(Object id) {
        this(id, null);
    }

    public IdentifierList(Object i, Object l) {
        this.id = (AST) i;
        this.list = Optional.ofNullable((AST) l);
        addChildren(id);
        addChildren(list);
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
