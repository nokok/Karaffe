/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree.modifiers;

import karaffe.compiler.tree.AbstractNode;
import karaffe.compiler.visitor.Visitor;

public class PublicModifier extends AbstractNode {

    @Override
    public void accept(Visitor visitor) {
        visitor.publicModifier(this);
    }

}
