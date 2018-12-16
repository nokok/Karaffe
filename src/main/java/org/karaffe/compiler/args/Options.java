package org.karaffe.compiler.args;

import org.karaffe.compiler.util.KaraffeSource;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

public class Options {

    private Set<Flag> flags;
    private Set<KaraffeSource> sources;

    public Options() {
        this(new HashSet<>(0), new HashSet<>(0));
    }

    public Options(Set<Flag> flags, Set<KaraffeSource> sources) {
        this.flags = Objects.requireNonNull(flags);
        this.sources = Objects.requireNonNull(sources);
    }

    public boolean isEmpty() {
        return this.flags.isEmpty();
    }

    public boolean hasFlag(Flag flag) {
        return this.flags.contains(flag);
    }

    public Stream<KaraffeSource> sourceStream() {
        return this.sources.stream();
    }
}
