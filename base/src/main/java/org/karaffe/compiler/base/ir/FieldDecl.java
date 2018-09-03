package org.karaffe.compiler.base.ir;

import org.karaffe.compiler.base.ir.util.Binding;
import org.karaffe.compiler.base.ir.util.KaraffeIRType;

import java.util.Objects;

public class FieldDecl implements Binding {

    private String name;
    private KaraffeIRType type;

    public FieldDecl(String name, KaraffeIRType type) {
        this.name = Objects.requireNonNull(name);
        this.type = Objects.requireNonNull(type);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public KaraffeIRType getType() {
        return this.type;
    }
}
