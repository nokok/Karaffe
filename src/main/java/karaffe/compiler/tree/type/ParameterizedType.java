/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree.type;

import karaffe.compiler.tree.AST;
import karaffe.compiler.tree.AbstractNode;
import karaffe.compiler.visitor.Visitor;

public class ParameterizedType extends AbstractNode {

    private final AST identifier;
    private final AST type;

    public ParameterizedType(Object i, Object t) {
        this.identifier = (AST) i;
        this.type = (AST) t;
        addChildren(identifier);
        addChildren(type);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.parameterizedType(this);
    }

}
