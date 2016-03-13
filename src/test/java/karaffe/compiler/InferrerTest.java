package karaffe.compiler.inferred;

import java.io.PrintStream;
import java.math.BigInteger;
import karaffe.compiler.AddExpr;
import karaffe.compiler.IntLiteral;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;

public class InferrerTest {

    @Test
    public void testGetInferredType1() {
        InsnList insnList = new InsnList();

        //System.out.println("HelloWorld!");
        insnList.add(new FieldInsnNode(Opcodes.GETSTATIC, Type.getInternalName(System.class), "out", Type.getDescriptor(PrintStream.class)));
        insnList.add(new LdcInsnNode("Hello World!"));
        insnList.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, Type.getInternalName(PrintStream.class), "println", Type.getMethodDescriptor(Type.VOID_TYPE, Type.getType(String.class)), false));
        insnList.add(new InsnNode(Opcodes.RETURN));
        Class<?> clazz = new Inferrer().getInferredType(insnList);
        assertThat(clazz.toString(), is(Void.class.toString()));
    }

    @Test
    public void testGetInferredType2() {
        InsnList insnList = new InsnList();

        //"HelloWorld!"
        insnList.add(new LdcInsnNode("Hello World!"));
        Class<?> clazz = new Inferrer().getInferredType(insnList);
        assertThat(clazz.toString(), is(String.class.toString()));
    }

    @Test
    public void testGetInferredType3() {
        IntLiteral a = new IntLiteral("0", -1, -1);
        IntLiteral b = new IntLiteral("1", -1, -1);
        AddExpr expr = new AddExpr(a, b, -1, -1, -1, -1);
        Class<?> clazz = new Inferrer().getInferredType(expr.toNode());
        assertThat(clazz.toString(), is(BigInteger.class.toString()));
    }

}
