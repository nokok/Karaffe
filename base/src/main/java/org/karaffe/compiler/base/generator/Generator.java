package org.karaffe.compiler.base.generator;

public interface Generator<T> {
    T generate();

    static Generator<String> defaultElementIdGenerator() {
        return defaultElementIdGenerator(false);
    }

    static Generator<String> defaultElementIdGenerator(boolean forUnitTest) {
        if (forUnitTest) {
            return new SerialNumberGenerator();
        }
        return new UUIDGenerator();
    }
}
