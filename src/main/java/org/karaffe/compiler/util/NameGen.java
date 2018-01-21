package org.karaffe.compiler.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.karaffe.compiler.tree.Name;

public class NameGen {
    private final AtomicLong atomicLong = new AtomicLong();
    private final String globalPrefix;
    private final List<String> generated = new ArrayList<>();

    public NameGen(String globalPrefix) {
        this.globalPrefix = globalPrefix;
    }

    public Name genName() {
        return genName("");
    }

    public Name genName(String prefix) {
        String generatedName = genNameInternal(prefix);
        while (!this.generated.add(generatedName)) {
            generatedName = genNameInternal(prefix);
        }
        Name name = new Name(generatedName);
        this.generated.add(name.getName());
        return name;
    }

    private String genNameInternal(String prefix) {
        long r = this.atomicLong.getAndIncrement();
        return String.format("%s%s%d", this.globalPrefix, prefix, r);
    }

}
