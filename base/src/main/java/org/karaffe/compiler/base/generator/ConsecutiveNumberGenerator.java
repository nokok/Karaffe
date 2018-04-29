package org.karaffe.compiler.base.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class ConsecutiveNumberGenerator implements Generator<String> {
    private final AtomicLong atomicLong = new AtomicLong();
    private final String globalPrefix;
    private final List<String> generated = new ArrayList<>();

    public ConsecutiveNumberGenerator(final String globalPrefix) {
        this.globalPrefix = globalPrefix;
    }

    private String genNameInternal() {
        final long r = this.atomicLong.getAndIncrement();
        String name = String.format("%s%d", this.globalPrefix, r);
        this.generated.add(name);
        return name;
    }

    @Override
    public String generate() {
        return genNameInternal();
    }

}
