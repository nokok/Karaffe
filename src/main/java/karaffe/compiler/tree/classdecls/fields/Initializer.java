/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree.classdecls.fields;

import java.util.Optional;
import karaffe.compiler.tree.AST;
import karaffe.compiler.tree.AbstractNode;
import karaffe.compiler.visitor.Visitor;

public class Initializer extends AbstractNode {

    private final Optional<AST> initializer;

    public Initializer() {
        initializer = Optional.empty();
    }

    public Initializer(Object expr) {
        this.initializer = Optional.of((AST) expr);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.initializer(this);
    }

}
