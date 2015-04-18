/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree.type;

import karaffe.compiler.tree.AbstractNode;
import karaffe.compiler.visitor.Visitor;

public class TypeName extends AbstractNode {

    public TypeName(Object n) {

    }

    @Override
    public void accept(Visitor visitor) {
        visitor.typeName(this);
    }
}
