package org.karaffe.compiler.base.generator;

import java.util.UUID;

class UUIDGenerator implements Generator<String> {

    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
