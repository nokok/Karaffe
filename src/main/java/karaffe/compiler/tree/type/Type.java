/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree.type;

import java.util.Optional;
import karaffe.compiler.tree.AST;
import karaffe.compiler.tree.AbstractNode;
import karaffe.compiler.visitor.Visitor;

public class Type extends AbstractNode {

    private final Optional<AST> type;

    public Type(Object t) {
        this.type = Optional.ofNullable((AST) t);
        addChildren(type);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.type(this);
    }

    @Override
    public String toString() {
        return "(Type:" + type.toString() + ")";
    }

}
