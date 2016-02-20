package karaffe.compiler;

import java.math.BigInteger;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.TypeInsnNode;

public class IntLiteral implements Expression {

    private final String value;
    private final Position pos;

    public IntLiteral(String i, int ileft, int iright) {
        this.value = i;
        this.pos = new Position(ileft, iright);
    }

    @Override
    public InsnList toNode() {
        InsnList list = new InsnList();
        list.add(new TypeInsnNode(Opcodes.NEW, Type.getInternalName(BigInteger.class)));
        list.add(new InsnNode(Opcodes.DUP));
        list.add(new LdcInsnNode(value));
        list.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, Type.getInternalName(BigInteger.class), "<init>", Type.getMethodDescriptor(Type.VOID_TYPE, Type.getType(String.class)), false));
        return list;
    }

    @Override
    public String toString() {
        return "(int-value " + value.toString() + ")";
    }

}
