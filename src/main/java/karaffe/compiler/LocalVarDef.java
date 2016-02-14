package karaffe.compiler;

import java.util.Objects;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.LocalVariableNode;
import org.objectweb.asm.tree.VarInsnNode;

class LocalVarDef implements Statement, NodeGeneratable<LocalVariableNode> {

    private final Expression e;
    private final Identifier id;
    private final TypeElement type;
    private MethodDef parent;
    private int index;
    private String path;

    LocalVarDef(Identifier id, TypeElement type, Expression e) {
        this.id = Objects.requireNonNull(id);
        this.type = Objects.requireNonNull(type);
        this.e = Objects.requireNonNull(e);
        this.id.setBody(this.e);
        Context.INSTANCE.add(this);
    }

    public String name() {
        return id.id();
    }

    public void setParent(MethodDef parent) {
        if ( this.parent != null ) {
            return;
        }
        this.parent = Objects.requireNonNull(parent);
        this.id.setParent(parent);
    }

    public void setPath(String path) {
        this.path = Objects.requireNonNull(path) + (path.isEmpty() ? "" : ".") + id.id();
        this.id.setPath(this.parent.getPath());
    }

    public MethodDef getParent() {
        return parent;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Class<?> inferredType() {
        return e.inferredType();
    }

    @Override
    public LocalVariableNode toNode() {
        LocalVariableNode node = new LocalVariableNode(id.id(), Context.INSTANCE.resolveInternalNameByIdent(type.id()).orElse("Object"), null, new LabelNode(), new LabelNode(), index);
        //indexが0のところ、descがtype.idになっているところ、スコープのラベルのstartとendをちゃんと実装すること
        return node;
    }

    public InsnList getExprInsnList() {
        InsnList insnList = new InsnList();
        insnList.add(e.toNode());
        insnList.add(new VarInsnNode(Opcodes.ASTORE, index));
        return insnList;
    }

    @Override
    public String toString() {
        return "(local-var-def " + String.join(" ", "parent:" + (parent == null ? "none" : parent.name()), path, type.toString(), e.toString(), "(index " + index + ")") + ")";
    }

    int getIndex() {
        return index;
    }

    @Override
    public String getPath() {
        return this.path;
    }

    public TypeElement getType() {
        return type;
    }
}
