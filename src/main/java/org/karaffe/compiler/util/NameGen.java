package org.karaffe.compiler.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.karaffe.compiler.tree.Name;

public class NameGen {
    private final AtomicLong atomicLong = new AtomicLong();
    private final String globalPrefix;
    private final List<String> generated = new ArrayList<>();

    public NameGen(final String globalPrefix) {
        this.globalPrefix = globalPrefix;
    }

    public Name genName() {
        return this.genName("");
    }

    public Name genName(final String prefix) {
        String generatedName = this.genNameInternal(prefix);
        while (!this.generated.add(generatedName)) {
            generatedName = this.genNameInternal(prefix);
        }
        final Name name = new Name(generatedName);
        this.generated.add(name.getName());
        return name;
    }

    private String genNameInternal(final String prefix) {
        final long r = this.atomicLong.getAndIncrement();
        return String.format("%s%s%d", this.globalPrefix, prefix, r);
    }

}
