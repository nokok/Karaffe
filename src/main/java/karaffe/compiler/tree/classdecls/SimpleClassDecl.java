/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree.classdecls;

import java.util.Optional;
import karaffe.compiler.tree.AST;
import karaffe.compiler.tree.AbstractNode;
import karaffe.compiler.tree.Identifier;
import karaffe.compiler.visitor.Visitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class SimpleClassDecl extends AbstractNode {

    private final Optional<AST> annotationList;
    private final Optional<AST> modifierList;
    private final Identifier identifier;
    private final Optional<AST> autoDeclList;
    private final Optional<AST> body;

    public SimpleClassDecl(Object a, Object m, Object id, Object b, Object body) {
        this.annotationList = Optional.ofNullable((AST) a);
        this.modifierList = Optional.ofNullable((AST) m);
        this.identifier = (Identifier) id;
        this.autoDeclList = Optional.ofNullable((AST) b);
        this.body = Optional.ofNullable((AST) body);
        annotationList.ifPresent(children::add);
        modifierList.ifPresent(children::add);
        children.add(this.identifier);
        autoDeclList.ifPresent(children::add);
        this.body.ifPresent(children::add);
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
        return identifier.name();
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
}
