package org.karaffe.compiler.base.ir.builtin;

import org.karaffe.compiler.base.ir.util.KaraffeIRType;

public class Boolean implements KaraffeIRType {
    private boolean value;

    private Boolean(boolean value) {
        this.value = value;
    }

    public static Boolean mkTrue() {
        return new Boolean(true);
    }

    public static Boolean mkFalse() {
        return new Boolean(false);
    }
}
