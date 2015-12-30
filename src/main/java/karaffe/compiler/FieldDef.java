package karaffe.compiler;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.FieldNode;

class FieldDef implements Statement, NodeGeneratable<FieldNode> {

    private final List<Annotation> annotations = new ArrayList<>();
    private final List<Modifier> modifiers = new ArrayList<>();
    private final Expression e;
    private final Identifier id;
    private final TypeElement type;
    private String path;
    private ClassDef parent;

    FieldDef(Identifier id, TypeElement type, Expression e) {
        this.id = Objects.requireNonNull(id);
        this.type = Objects.requireNonNull(type);
        this.e = Objects.requireNonNull(e);
        Context.INSTANCE.add(this);
    }

    FieldDef(List<Annotation> a, List<Modifier> m, Identifier id, TypeElement type, Expression e) {
        this.annotations.addAll(a);
        this.modifiers.addAll(m);
        this.e = e;
        this.id = id;
        this.type = type;
        Context.INSTANCE.add(this);
    }

    @Override
    public String getPath() {
        return path;
    }

    public Identifier id() {
        return id;
    }

    public TypeElement type() {
        return type;
    }

    public Expression expr() {
        return e;
    }

    @Override
    public Class<?> inferredType() {
        return e.inferredType();
    }

    public void setParent(ClassDef classDef) {
        if ( this.parent != null ) {
            return;
        }
        this.parent = classDef;
    }

    public ClassDef getParent() {
        return parent;
    }

    public void setPath(String path) {
        if ( path != null ) {
            throw new IllegalStateException();
        }
        this.path = Objects.requireNonNull(path);
    }

    @Override
    public FieldNode toNode() {
        FieldNode fieldNode = new FieldNode(Opcodes.ACC_PUBLIC, id.toString(), type.toString(), null, null);
        return fieldNode;
    }

    @Override
    public String toString() {
        return "(field-def: " + String.join(" ", id.toString(), type.toString(), e.toString()) + ")";
    }

}
