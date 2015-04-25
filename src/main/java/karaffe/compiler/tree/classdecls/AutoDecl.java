/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree.classdecls;

import karaffe.compiler.tree.ASMConvertible;
import karaffe.compiler.tree.AST;
import karaffe.compiler.tree.AbstractNode;
import karaffe.compiler.tree.Identifier;
import karaffe.compiler.visitor.Visitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.FieldNode;

public class AutoDecl extends AbstractNode implements ASMConvertible<FieldNode> {

    private final AST id;
    private final karaffe.compiler.tree.type.Type type;

    public AutoDecl(Object id, Object t) {
        this.id = (AST) id;
        this.type = (karaffe.compiler.tree.type.Type) t;
        addChildren(this.id);
        addChildren(this.type);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.autoDecl(this);
    }

    @Override
    public String toString() {
        return "(AutoDecl:" + String.join(",", id.toString(), type.toString()) + ")";
    }

    public String name() {
        return Identifier.class.cast(id).get();
    }

    public String desc() {
        return type.toNode().getDescriptor();
    }

    public String signature() {
        return null;
    }

    @Override
    public FieldNode toNode() {
        return new FieldNode(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL, name(), desc(), signature(), null);
    }
}
