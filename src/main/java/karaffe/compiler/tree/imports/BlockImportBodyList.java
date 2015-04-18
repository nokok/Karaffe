/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree.imports;

import java.util.Optional;
import karaffe.compiler.tree.AST;
import karaffe.compiler.tree.AbstractNode;
import karaffe.compiler.visitor.Visitor;

public class BlockImportBodyList extends AbstractNode {

    private final AST body;
    private final Optional<AST> list;

    public BlockImportBodyList(Object b, Object l) {
        this.body = (AST) b;
        this.list = Optional.ofNullable((AST) l);
        addChildren(body);
        addChildren(list);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.blockImportBodyList(this);
    }

    @Override
    public String toString() {
        return "(BlockImportBodyList:" + body + "," + list + ")";
    }
}
