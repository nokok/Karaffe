/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree.classdecls;

import karaffe.compiler.tree.AST;
import karaffe.compiler.tree.AbstractNode;
import karaffe.compiler.visitor.Visitor;

public class ClassBody extends AbstractNode {

    private final AST fieldDecl;

    public ClassBody(Object f) {
        this.fieldDecl = (AST) f;
        children.add(fieldDecl);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.classBody(this);
    }

    @Override
    public String toString() {
        return "(ClassBody:" + fieldDecl.toString() + ")";
    }

}
