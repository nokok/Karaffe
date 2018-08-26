package org.karaffe.compiler.base.ir.builtin;

import org.karaffe.compiler.base.ir.util.KaraffeIRType;

public class Float32 implements KaraffeIRType {
    private float value;

    public Float32(float value) {
        this.value = value;
    }

    public Float32(String value) {
        this.value = Long.valueOf(value);
    }
}
