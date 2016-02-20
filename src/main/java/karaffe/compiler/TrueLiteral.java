package karaffe.compiler;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnList;

class TrueLiteral implements Expression {

    public TrueLiteral() {

    }

    @Override
    public InsnList toNode() {
        InsnList list = new InsnList();
        list.add(new FieldInsnNode(Opcodes.GETSTATIC, Type.getInternalName(Boolean.class), "TRUE", Type.getDescriptor(Boolean.class)));
        return list;
    }

    @Override
    public String toString() {
        return "(boolean-value true)";
    }

}
