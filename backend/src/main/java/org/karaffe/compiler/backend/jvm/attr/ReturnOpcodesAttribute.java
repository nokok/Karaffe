package org.karaffe.compiler.backend.jvm.attr;

import net.nokok.azm.Opcodes;
import org.karaffe.compiler.base.mir.util.attr.Attribute;

public class ReturnOpcodesAttribute extends Attribute {
    private int opcodes;

    public ReturnOpcodesAttribute(int opcodes) {
        this.opcodes = opcodes;
        switch (this.opcodes) {
        case Opcodes.IRETURN:
        case Opcodes.LRETURN:
        case Opcodes.FRETURN:
        case Opcodes.DRETURN:
        case Opcodes.ARETURN:
        case Opcodes.RETURN:
            return;
        default:
            throw new IllegalArgumentException(String.valueOf(this.opcodes));
        }
    }

    public int getOpcodes() {
        return opcodes;
    }
}
