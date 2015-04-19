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
import karaffe.compiler.tree.Identifier;
import karaffe.compiler.visitor.Visitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;

public class SimpleClassDecl extends AbstractNode implements ASMConvertible<ClassNode> {

    private final Optional<AST> annotationList;
    private final Optional<AST> modifierList;
    private final Identifier identifier;
    private final Optional<AST> autoDeclList;
    private final Optional<AST> body;

    public SimpleClassDecl(Object a, Object m, Object id, Object b, Object ex, Object bd) {
        this.annotationList = Optional.ofNullable((AST) a);
        this.modifierList = Optional.ofNullable((AST) m);
        this.identifier = (Identifier) id;
        this.autoDeclList = Optional.ofNullable((AST) bd);
        this.body = Optional.ofNullable((AST) bd);
        addChildren(annotationList);
        addChildren(modifierList);
        addChildren(identifier);
        addChildren(autoDeclList);
        addChildren(body);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.simpleClassDecl(this);
    }

    @Override
    public String toString() {
        return "(SimpleClassDecl:" + String.join(",", annotationList.toString(), modifierList.toString(), identifier.toString(), autoDeclList.toString(), body.toString()) + ")";
    }

    public int access() {
        return Opcodes.ACC_PUBLIC;
    }

    public String name() {
        return identifier.get();
    }

    public String signature() {
        return null;
    }

    public String superName() {
        return Type.getInternalName(Object.class);
    }

    public String[] interfaces() {
        return null;
    }

    public List<String> interfacesList() {
        return new ArrayList<>();
    }

    @Override
    public ClassNode toNode() {
        ClassNode classNode = new ClassNode();
        classNode.access = access();
        classNode.name = name();
        classNode.signature = signature();
        classNode.superName = superName();
        classNode.interfaces = interfacesList();
        classNode.version = Opcodes.V1_8;
        List<FieldNode> fieldNodes = new ArrayList<>();
        body.ifPresent(b -> ClassBody.class.cast(body).toNode());
        return classNode;
    }
}
