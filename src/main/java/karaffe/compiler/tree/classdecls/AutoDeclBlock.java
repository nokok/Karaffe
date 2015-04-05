/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree.classdecls;

import karaffe.compiler.tree.AbstractNode;
import karaffe.compiler.visitor.Visitor;

public class AutoDeclBlock extends AbstractNode {

    public AutoDeclBlock(Object l) {
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.autoDeclBlock(this);
    }

}
