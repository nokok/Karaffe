/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree.classdecls;

import karaffe.compiler.tree.AbstractNode;
import karaffe.compiler.visitor.Visitor;

public class ExtendsOrImplements extends AbstractNode {

    public ExtendsOrImplements(Object t) {

    }

    @Override
    public void accept(Visitor visitor) {
        visitor.extendsOrImplements(this);
    }
}
