package org.karaffe.compiler.base.mir;

import org.karaffe.compiler.base.mir.transformer.InstructionTransformer;

public class A {
    public void doSomething() {
        InstructionTransformer build = InstructionTransformer
                .begin(InstructionType.BEGINCLASS, "#A")
                .toEnd().build();
    }
}
