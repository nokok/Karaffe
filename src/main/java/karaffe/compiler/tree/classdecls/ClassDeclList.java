/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree.classdecls;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import karaffe.compiler.tree.ASMConvertible;
import karaffe.compiler.tree.AST;
import karaffe.compiler.tree.AbstractNode;
import karaffe.compiler.visitor.Visitor;
import org.objectweb.asm.tree.ClassNode;

public class ClassDeclList extends AbstractNode implements ASMConvertible<List<ClassNode>> {

    private final AST classDecl;
    private final Optional<AST> classDeclList;

    public ClassDeclList(Object c, Object l) {
        this.classDecl = (AST) c;
        this.classDeclList = Optional.ofNullable((AST) l);
        addChildren(classDecl);
        addChildren(classDeclList);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.classDeclList(this);
    }

    @Override
    public String toString() {
        return "(ClassDeclList:" + String.join(",", classDecl.toString(), classDeclList.toString()) + ")";
    }

    @Override
    public List<ClassNode> toNode() {
        List<ClassNode> classNodes = new ArrayList<>();
        classNodes.add(ClassDecl.class.cast(classDecl).toNode());
        classDeclList.ifPresent(c -> classNodes.addAll(ClassDeclList.class.cast(c).toNode()));
        return classNodes;
    }

}
