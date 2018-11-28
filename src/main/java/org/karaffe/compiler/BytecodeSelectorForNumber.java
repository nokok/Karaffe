package org.karaffe.compiler;

import net.nokok.azm.Opcodes;
import net.nokok.azm.tree.AbstractInsnNode;
import net.nokok.azm.tree.InsnNode;
import net.nokok.azm.tree.IntInsnNode;
import net.nokok.azm.tree.LdcInsnNode;

public class BytecodeSelectorForNumber {

    public static AbstractInsnNode fromInt(int value) {
        switch (value) {
        case -1:
            return new InsnNode(Opcodes.ICONST_M1);
        case 0:
            return new InsnNode(Opcodes.ICONST_0);
        case 1:
            return new InsnNode(Opcodes.ICONST_1);
        case 2:
            return new InsnNode(Opcodes.ICONST_2);
        case 3:
            return new InsnNode(Opcodes.ICONST_3);
        case 4:
            return new InsnNode(Opcodes.ICONST_4);
        case 5:
            return new InsnNode(Opcodes.ICONST_5);
        }
        if (Byte.MIN_VALUE <= value && value <= Byte.MAX_VALUE) {
            return new IntInsnNode(Opcodes.BIPUSH, value);
        }
        if (Short.MIN_VALUE <= value && value <= Short.MAX_VALUE) {
            return new IntInsnNode(Opcodes.SIPUSH, value);
        }
        return new LdcInsnNode(value);
    }
}