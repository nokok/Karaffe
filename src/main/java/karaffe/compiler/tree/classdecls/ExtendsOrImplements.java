/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree.classdecls;

import karaffe.compiler.tree.AST;
import karaffe.compiler.tree.AbstractNode;
import karaffe.compiler.visitor.Visitor;

public class ExtendsOrImplements extends AbstractNode {

    private final AST t;

    public ExtendsOrImplements(Object t) {
        this.t = (AST) t;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.extendsOrImplements(this);
    }
}
