package org.karaffe.compiler.backend.common.generators;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.karaffe.compiler.ast.names.SimpleName;

public class ConsecutiveNumberGenerator {
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

    public String genName(){
        return genNameInternal();
    }

    public SimpleName genSimpleName() {
        String name = genNameInternal();
        this.generated.add(name);
        SimpleName simpleName = new SimpleName(name);
        return simpleName;
    }

}
