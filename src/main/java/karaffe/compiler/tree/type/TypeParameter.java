/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree.type;

import karaffe.compiler.tree.AST;
import karaffe.compiler.tree.AbstractNode;
import karaffe.compiler.visitor.Visitor;

public class TypeParameter extends AbstractNode {

    private final AST type;

    public TypeParameter(Object t) {
        this.type = (AST) t;
        children.add(this.type);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.typeParameter(this);
    }

}
