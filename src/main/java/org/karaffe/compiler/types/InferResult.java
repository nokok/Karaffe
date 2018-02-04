package org.karaffe.compiler.types;

import java.util.List;
import java.util.Optional;

import org.karaffe.compiler.context.TypeInferenceContext;
import org.karaffe.compiler.tree.Name;

import karaffe.core.Any;

public interface InferResult {

    public static String defaultTypeName() {
        return Any.class.getCanonicalName();
    }

    public static InferResult failed() {
        return new Failed();
    }

    public static InferResult of(final Class<? extends Any> clazz) {
        return new Successful(clazz.getCanonicalName());
    }

    public static InferResult of(final String... clazz) {
        return new Successful(clazz);
    }

    public static InferResult of(final List<String> clazz) {
        return new Successful(clazz);
    }

    public static InferResult ofEmpty() {
        return new Successful("karaffe.core.Any");
    }

    public static InferResult mayBeApplicable(InferResult ownerType, Name member) {
        return new MayBeApplicable(ownerType, member);
    }

    public static InferResult applying(MayBeApplicable applicable, List<InferResult> args) {
        return new Applying(applicable, args);
    }

    public static Undecidable noHint() {
        return new NoHint();
    }

    public static Undecidable anyTarget(Name name) {
        return new AnyTarget(name);
    }

    public default boolean isNotComplete() {
        return !this.isComplete();
    }

    public boolean isComplete();

    public boolean isFailed();

    public ResultType getInferResultType();

    public Optional<InferResult> compose(InferResult other, TypeInferenceContext context);

    public default Optional<String> getType() {
        return Optional.empty();
    }

    public enum ResultType {
        FAILED,
        SUCCESSFUL,
        NO_HINT,
        ANY_TARGET,
        MAY_BE_APPLICABLE,
        APPLYING
    }
}
