package karaffe.compiler;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;

public interface Expression extends NodeGeneratable<InsnList>, Inferable {

    public static final Expression UNINITIALIZED = new Expression() {

        @Override
        public Class<?> inferredType() {
            return Object.class;
        }

        @Override
        public InsnList toNode() {
            InsnList insnList = new InsnList();
            insnList.add(new InsnNode(Opcodes.NOP));
            return insnList;
        }
    };
}
