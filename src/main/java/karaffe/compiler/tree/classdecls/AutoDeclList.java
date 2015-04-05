/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree.classdecls;

import karaffe.compiler.tree.AbstractNode;
import karaffe.compiler.visitor.Visitor;

public class AutoDeclList extends AbstractNode {

    public AutoDeclList(Object d) {
    }

    public AutoDeclList(Object d, Object l) {
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.autoDeclList(this);
    }

}
