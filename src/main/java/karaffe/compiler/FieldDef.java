package karaffe.compiler;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.FieldNode;

class FieldDef implements Statement, NodeGeneratable<FieldNode> {

    private final Expression e;
    private final Identifier id;
    private final TypeElement type;
    private ClassDef parent;

    FieldDef(Identifier id, TypeElement type, Expression e) {
        this.id = id;
        this.type = type;
        this.e = e;
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
