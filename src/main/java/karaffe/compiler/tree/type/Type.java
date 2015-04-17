/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree.type;

import karaffe.compiler.tree.AST;
import karaffe.compiler.tree.AbstractNode;
import karaffe.compiler.visitor.Visitor;

public class Type extends AbstractNode {

    private final AST type;

    public Type(Object t) {
        this.type = (AST) t;
        addChildren(type);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.type(this);
    }

}
