package org.karaffe.compiler.base.generator;

public interface Generator<T> {
    static Generator<String> defaultElementIdGenerator() {
        return defaultElementIdGenerator(false);
    }

    static Generator<String> defaultElementIdGenerator(boolean forUnitTest) {
        return new UnderScoredSerialNumberGenerator();
    }

    T generate();
}
