/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree.classdecls;

import java.util.Optional;
import karaffe.compiler.tree.AST;
import karaffe.compiler.tree.AbstractNode;
import karaffe.compiler.visitor.Visitor;

public class ClassBody extends AbstractNode {

    private final AST fieldDecl;
    private final Optional<AST> classBody;

    public ClassBody(Object f, Object b) {
        this.fieldDecl = (AST) f;
        this.classBody = Optional.ofNullable((AST) b);
        addChildren(fieldDecl);
        addChildren(classBody);
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
