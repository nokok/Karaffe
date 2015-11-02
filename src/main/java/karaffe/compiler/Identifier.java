package karaffe.compiler;

import java.util.Objects;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.VarInsnNode;

public class Identifier implements Expression, NodeGeneratable<InsnList> {

    private static final String NONE = "$$none$$";
    private Expression body;
    private MethodDef parent;

    public static final Identifier NONE_IDENTIFIER = new Identifier(NONE, -1, -1);

    static Identifier none() {
        return NONE_IDENTIFIER;
    }

    private final String id;
    private final Position pos;
    private String path;

    public void setPath(String path) {
        this.path = Objects.requireNonNull(path) + (path.isEmpty() ? "" : ".") + id;
    }

    public String getPath() {
        return this.path;
    }

    Identifier(String id, int idleft, int idright) {
        this.id = id;
        this.pos = new Position(idleft, idright);
        Context.INSTANCE.add(this);
    }

    public String id() {
        return id;
    }

    public int getLine() {
        return pos.getLine();
    }

    public int getColumn() {
        return pos.getColumn();
    }

    public void setParent(MethodDef methodDef) {
        if ( this.parent != null ) {
            return;
        }
        this.parent = Objects.requireNonNull(methodDef);
    }

    public MethodDef getParent() {
        return parent;
    }

    public void setBody(Expression expression) {
        if ( this.body != null ) {
            return;
        }
        this.body = expression;
    }

    @Override
    public Class<?> inferredType() {
        if ( body == null ) {
            return Object.class;
        }
        return body.inferredType();
    }

    @Override
    public InsnList toNode() {
        InsnList insnList = new InsnList();
        if ( path != null ) {
            int index = Context.INSTANCE.findLocalVarIndex(path);
            insnList.add(new VarInsnNode(Opcodes.ALOAD, index));
        }
        return insnList;
    }

    @Override
    public String toString() {
        return "(id " + id + pos + ")";
    }

    public static boolean isNone(Identifier identifier) {
        return identifier.id().equals(NONE);
    }

    public static boolean isNone(String id) {
        return id.equals(NONE);
    }

}
