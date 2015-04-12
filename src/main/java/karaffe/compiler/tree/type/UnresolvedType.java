/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree.type;

import karaffe.compiler.tree.AbstractNode;
import karaffe.compiler.visitor.Visitor;

public class UnresolvedType extends AbstractNode {

    @Override
    public void accept(Visitor visitor) {
        visitor.unresolvedType(this);
    }

}
