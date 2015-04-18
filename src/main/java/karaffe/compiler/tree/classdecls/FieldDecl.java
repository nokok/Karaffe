/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree.classdecls;

import java.util.Optional;
import karaffe.compiler.tree.AST;
import karaffe.compiler.tree.AbstractNode;
import karaffe.compiler.visitor.Visitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class FieldDecl extends AbstractNode {

    private final Optional<AST> annotationList;
    private final Optional<AST> modifierList;
    private final AST identifier;
    private final Optional<AST> type;
    private final Optional<AST> initializer;

    public FieldDecl(Object a, Object m, Object id, Object t, Object init) {
        this.annotationList = Optional.ofNullable((AST) a);
        this.modifierList = Optional.ofNullable((AST) m);
        this.identifier = (AST) id;
        this.type = Optional.ofNullable((AST) t);
        this.initializer = Optional.ofNullable((AST) init);
        addChildren(annotationList);
        addChildren(modifierList);
        addChildren(identifier);
        addChildren(type);
        addChildren(initializer);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.fieldDecl(this);
    }

    public int access() {
        return Opcodes.ACC_PUBLIC;
    }

    public String name() {
        return "hoge";
    }

    public String desc() {
        karaffe.compiler.tree.type.Type t = (karaffe.compiler.tree.type.Type) type.get();
        return Type.getDescriptor(Object.class);
    }

    public String signature() {
        return null;
    }

    public Object value() {
        return null;
    }

    @Override
    public String toString() {
        return "(FieldDecl:" + String.join(",", annotationList.toString(), modifierList.toString(), identifier.toString(), type.toString(), initializer.toString()) + ")";
    }

    @Override
    public FieldNode toNode() {
        return new FieldNode(access(), name(), desc(), signature(), value());
    }
}
