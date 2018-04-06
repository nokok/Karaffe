package org.karaffe.compiler.base.generator;

import org.karaffe.compiler.base.types.constraints.TypeVar;

import java.util.ArrayList;
import java.util.List;

public class TypeVarGenerator implements Generator<TypeVar> {

    private final ConsecutiveNumberGenerator generator = new ConsecutiveNumberGenerator("?");
    private final List<TypeVar> generated = new ArrayList<>();

    @Override
    public TypeVar generate() {
        TypeVar var = new TypeVar(this.generator.generate());
        this.generated.add(var);
        return var;
    }
}
