package org.karaffe.compiler.base.types.constraints;

import java.util.*;
import java.util.function.Consumer;

public class TypeConstraints implements Iterable<TypeConstraint> {

    private final List<TypeConstraint> constraints = new ArrayList<>();

    public void add(TypeConstraint constraint) {
        if (this.constraints.contains(constraint)) {
            return;
        }
        this.constraints.add(Objects.requireNonNull(constraint));
    }

    @Override
    public Iterator<TypeConstraint> iterator() {
        return this.constraints.iterator();
    }

    @Override
    public void forEach(Consumer<? super TypeConstraint> action) {
        this.constraints.forEach(action);
    }

    @Override
    public Spliterator<TypeConstraint> spliterator() {
        return this.constraints.spliterator();
    }
}
