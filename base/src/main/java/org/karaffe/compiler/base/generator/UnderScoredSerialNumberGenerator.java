package org.karaffe.compiler.base.generator;

class UnderScoredSerialNumberGenerator implements Generator<String> {
    private long value = 0L;

    @Override
    public String generate() {
        return "_" + this.value++;
    }
}
