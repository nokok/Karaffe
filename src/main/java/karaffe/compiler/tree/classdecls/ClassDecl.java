/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree.classdecls;

import karaffe.compiler.tree.AST;
import karaffe.compiler.tree.AbstractNode;
import karaffe.compiler.visitor.Visitor;

public class ClassDecl extends AbstractNode {

    private final AST classDecl;

    public ClassDecl(Object c) {
        this.classDecl = (AST) c;
        children.add(this.classDecl);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.classDecl(this);
    }

    @Override
    public String toString() {
        return "(ClassDecl:" + classDecl.toString() + ")";
    }

}
