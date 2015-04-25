/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree.classdecls;

import karaffe.compiler.tree.ASMConvertible;
import karaffe.compiler.tree.AST;
import karaffe.compiler.tree.AbstractNode;
import karaffe.compiler.visitor.Visitor;
import org.objectweb.asm.tree.ClassNode;

public class ClassDecl extends AbstractNode implements ASMConvertible<ClassNode> {

    private final AST classDecl;

    public ClassDecl(Object c) {
        this.classDecl = (AST) c;
        addChildren(classDecl);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.classDecl(this);
    }

    @Override
    public String toString() {
        return "(ClassDecl:" + classDecl.toString() + ")";
    }

    @Override
    public ClassNode toNode() {
        ASMConvertible<ClassNode> convertible = ASMConvertible.class.cast(classDecl);
        return convertible.toNode();
    }

}
