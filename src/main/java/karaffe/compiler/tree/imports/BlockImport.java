/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree.imports;

import java.util.Optional;
import karaffe.compiler.tree.AST;
import karaffe.compiler.tree.AbstractNode;
import karaffe.compiler.visitor.Visitor;

public class BlockImport extends AbstractNode {

    private final Optional<AST> body;

    public BlockImport(Object b) {
        this.body = Optional.ofNullable((AST) b);
        addChildren(body);
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
