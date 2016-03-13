package karaffe.compiler;

import java.io.PrintStream;
import java.util.List;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodInsnNode;

class MethodInvocation implements Expression {

    private final List<Argument> args;
    private final Identifier methodName;
    private final Expression target;

    public MethodInvocation(Expression target, Identifier methodName, List<Argument> e) {
        this.target = target;
        this.methodName = methodName;
        this.args = e;
    }

    @Override
    public InsnList toNode() {
        InsnList insnList = new InsnList();
        if ( methodName.id().equals("println") ) {
            insnList.add(new FieldInsnNode(Opcodes.GETSTATIC, Type.getInternalName(System.class), "out", Type.getDescriptor(PrintStream.class)));
            Argument arg = args.get(0);
            insnList.add(arg.toNode());
            insnList.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, Type.getInternalName(PrintStream.class), "println", Type.getMethodDescriptor(Type.VOID_TYPE, Type.getType(Object.class)), false));
        } else {
            for ( Argument arg : args ) {
                insnList.add(arg.toNode());
            }
        }
        return insnList;
    }

    @Override
    public String toString() {
        return "(method-invocation " + String.join(" ", target.toString(), methodName.toString(), args.toString()) + ")";
    }

    public Expression getTarget() {
        return target;
    }

    public Identifier methodName() {
        return methodName;
    }

    public List<Argument> args() {
        return args;
    }
}
