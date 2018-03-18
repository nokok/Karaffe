package org.karaffe.compiler.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.karaffe.compiler.tree.Name;
import org.karaffe.compiler.tree.v2.names.SimpleName;

public class NameGen {
    private final AtomicLong atomicLong = new AtomicLong();
    private final String globalPrefix;
    private final List<String> generated = new ArrayList<>();

    public NameGen(final String globalPrefix) {
        this.globalPrefix = globalPrefix;
    }

    @Deprecated
    public Name genName() {
        return this.genName("");
    }

    @Deprecated
    public Name genName(final String prefix) {
        String generatedName = this.genNameInternal();
        while (!this.generated.add(generatedName)) {
            generatedName = this.genNameInternal();
        }
        final Name name = new Name(generatedName);
        this.generated.add(name.getName());
        return name;
    }

    private String genNameInternal() {
        final long r = this.atomicLong.getAndIncrement();
        return String.format("%s%d", this.globalPrefix, r);
    }

    public SimpleName genSimpleName() {
        String name = genNameInternal();
        this.generated.add(name);
        SimpleName simpleName = new SimpleName(name);
        return simpleName;
    }

}
