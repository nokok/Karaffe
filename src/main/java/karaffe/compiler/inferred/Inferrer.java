package karaffe.compiler.inferred;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.TypeInsnNode;

public class Inferrer {

    private final Stack<Class<?>> stack = new Stack<>();

    public Class<?> getInferredType(InsnList insnList) {
        List<AbstractInsnNode> list = Arrays.asList(insnList.toArray());

        if ( list.get(list.size() - 1).getOpcode() == Opcodes.RETURN ) {
            return Void.class;
        }

        for ( AbstractInsnNode node : list ) {
            if ( node instanceof LdcInsnNode ) {
                LdcInsnNode n = (LdcInsnNode) node;
                stack.push(n.cst.getClass());
            } else if ( node instanceof TypeInsnNode ) {
                TypeInsnNode t = (TypeInsnNode) node;
                Type type = Type.getType(t.desc);
                stack.push(typeToClass(type));
            } else if ( node instanceof InsnNode ) {
                InsnNode i = (InsnNode) node;
                System.out.println("Insn:" + i);
            } else if ( node instanceof MethodInsnNode ) {
                MethodInsnNode m = (MethodInsnNode) node;
                int argSize = Type.getArgumentTypes(m.desc).length;
                while ( argSize > 0 ) {
                    stack.pop();
                    argSize--;
                }
                Type type = Type.getReturnType(m.desc);
                if ( !type.equals(Type.VOID_TYPE) ) {
                    stack.push(typeToClass(type));
                }
            } else {
                System.out.println("other:" + node.getClass());
            }
        }
        return stack.peek();
    }

    private Class<?> typeToClass(Type t) {
        try {
            return Class.forName(t.getInternalName().replaceAll("/", "."));
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
}
