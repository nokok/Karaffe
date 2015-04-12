/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree.classdecls;

import karaffe.compiler.tree.AST;
import karaffe.compiler.tree.AbstractNode;
import karaffe.compiler.visitor.Visitor;

public class AutoDecl extends AbstractNode {

    private final AST id;
    private final AST type;

    public AutoDecl(Object id, Object t) {
        this.id = (AST) id;
        this.type = (AST) t;
        children.add(this.id);
        children.add(this.type);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.autoDecl(this);
    }

    @Override
    public String toString() {
        return "(AutoDecl:" + String.join(",", id.toString(), type.toString()) + ")";
    }
}
