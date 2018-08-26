package org.karaffe.compiler.base.generator;

class SerialNumberGenerator implements Generator<String> {
    private long value = 0L;

    @Override
    public String generate() {
        return "_" + this.value++;
    }
}
