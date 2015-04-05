/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree.classdecls;

import karaffe.compiler.tree.AbstractNode;
import karaffe.compiler.visitor.Visitor;

public class AutoDecl extends AbstractNode {

    public AutoDecl(Object id, Object t) {
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.autoDecl(this);
    }

}
