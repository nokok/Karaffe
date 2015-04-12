/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree.classdecls;

import java.util.Optional;
import karaffe.compiler.tree.AST;
import karaffe.compiler.tree.AbstractNode;
import karaffe.compiler.visitor.Visitor;

public class ClassDeclList extends AbstractNode {

    private final AST classDecl;
    private final Optional<AST> classDeclList;

    public ClassDeclList(Object c, Object l) {
        this.classDecl = (AST) c;
        this.classDeclList = Optional.ofNullable((AST) l);
        children.add(classDecl);
        classDeclList.ifPresent(children::add);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.classDeclList(this);
    }

    @Override
    public String toString() {
        return "(ClassDeclList:" + String.join(",", classDecl.toString(), classDeclList.toString()) + ")";
    }

}
