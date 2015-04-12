/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree.classdecls.fields;

import karaffe.compiler.tree.AbstractNode;
import karaffe.compiler.visitor.Visitor;

public class Initializer extends AbstractNode {

    public Initializer() {

    }

    public Initializer(Object expr) {

    }

    @Override
    public void accept(Visitor visitor) {
        visitor.initializer(this);
    }

}
