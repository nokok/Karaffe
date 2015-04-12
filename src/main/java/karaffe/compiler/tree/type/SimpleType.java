/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree.type;

import karaffe.compiler.tree.AST;
import karaffe.compiler.tree.AbstractNode;
import karaffe.compiler.visitor.Visitor;

public class SimpleType extends AbstractNode {

    private final AST simpleType;

    public SimpleType(Object s) {
        this.simpleType = (AST) s;
        children.add(this.simpleType);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.simpleType(this);
    }

}
