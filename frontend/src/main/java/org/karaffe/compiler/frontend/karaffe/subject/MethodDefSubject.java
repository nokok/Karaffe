package org.karaffe.compiler.frontend.karaffe.subject;

import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.def.MethodDef;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public interface MethodDefSubject<P> extends ToVisitor<P> {
    void onMethodDef(BiFunction<MethodDef, P, Tree> f);

    default void onMethodDef(Function<MethodDef, Tree> f) {
        Objects.requireNonNull(f);
        this.onMethodDef((def, ignored) -> f.apply(def));
    }

    default void onMethodDef(Consumer<MethodDef> f) {
        Objects.requireNonNull(f);
        this.onMethodDef(methodDef -> {
            f.accept(methodDef);
            return methodDef;// Function<MethodDef, Void>
        });
    }

    static <P> MethodDefSubject<P> getSubject() {
        return new MethodDefSubjectImpl<>();
    }
}
