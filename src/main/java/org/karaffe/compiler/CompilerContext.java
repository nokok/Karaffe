package org.karaffe.compiler;

import java.io.File;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

import org.karaffe.compiler.ConfigKeys.Config;
import org.karaffe.compiler.ConfigKeys.FlagConfigs;
import org.karaffe.compiler.ConfigKeys.StringConfigs;
import org.karaffe.compiler.pos.Position;
import org.karaffe.compiler.util.SourceContainer;

public interface CompilerContext {
    public Stream<File> sourceStream();

    public boolean hasSource();

    public static CompilerContext defaultContext(final Set<File> sourceFiles) {
        final SourceContainer container = new SourceContainer(sourceFiles);
        return new CompilerContextImpl(container);
    }
}

class CompilerContextImpl implements CompilerContext {

    private final SourceContainer sourceContainer;
    private final Map<ConfigKeys.FlagConfigs, Boolean> flags;
    private final Map<ConfigKeys.StringConfigs, String> stringConfigs;

    public CompilerContextImpl() {
        this(new SourceContainer());
    }

    public CompilerContextImpl(final SourceContainer sourceRepository) {
        this.sourceContainer = sourceRepository;
        this.flags = new EnumMap<>(ConfigKeys.FlagConfigs.class);
        this.stringConfigs = new EnumMap<>(ConfigKeys.StringConfigs.class);
    }

    @Override
    public Stream<File> sourceStream() {
        return this.sourceContainer.sourceStream();
    }

    @Override
    public boolean hasSource() {
        return !this.sourceContainer.isEmpty();
    }

    public void add(final FlagConfigs config) {
        this.flags.put(config, Boolean.TRUE);
    }

    @SuppressWarnings("boxing")
    public void add(final FlagConfigs config, final boolean value) {
        this.flags.put(config, value);
    }

    public void add(final StringConfigs config, final String value) {
        this.stringConfigs.put(config, Objects.requireNonNull(value));
    }

    public void remove(final Config config) {
        this.flags.remove(config);
        this.stringConfigs.remove(config);
    }

}
