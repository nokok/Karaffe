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

    public ParameterizedType(Object identifier, Object type) {
        this.identifier = (AST) identifier;
        this.type = (AST) type;
        children.add(this.identifier);
        children.add(this.type);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.parameterizedType(this);
    }

}
