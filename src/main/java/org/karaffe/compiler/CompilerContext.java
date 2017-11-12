package org.karaffe.compiler;

import java.io.File;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import org.karaffe.compiler.ConfigKeys.Config;
import org.karaffe.compiler.ConfigKeys.FlagConfigs;
import org.karaffe.compiler.ConfigKeys.StringConfigs;
import org.karaffe.compiler.util.SourceContainer;

public interface CompilerContext {
    public Stream<File> sourceStream();

    public boolean hasSource();

    public static CompilerContext defaultContext() {
        return new CompilerContextImpl();
    }
}

class CompilerContextImpl implements CompilerContext {

    private final SourceContainer sourceRepository;
    private final Map<ConfigKeys.FlagConfigs, Boolean> configs;
    private final Map<ConfigKeys.StringConfigs, String> stringConfigs;

    public CompilerContextImpl() {
        this(new SourceContainer());
    }

    public CompilerContextImpl(final SourceContainer sourceRepository) {
        this.sourceRepository = sourceRepository;
        this.configs = new EnumMap<>(ConfigKeys.FlagConfigs.class);
        this.stringConfigs = new EnumMap<>(ConfigKeys.StringConfigs.class);
    }

    @Override
    public Stream<File> sourceStream() {
        return this.sourceRepository.sourceStream();
    }

    @Override
    public boolean hasSource() {
        return !this.sourceRepository.isEmpty();
    }

    public void add(final File f) {
        this.sourceRepository.add(f);
    }

    public void add(final FlagConfigs config) {
        this.configs.put(config, Boolean.TRUE);
    }

    @SuppressWarnings("boxing")
    public void add(final FlagConfigs config, final boolean value) {
        this.configs.put(config, value);
    }

    public void add(final StringConfigs config, final String value) {
        this.stringConfigs.put(config, Objects.requireNonNull(value));
    }

    public void remove(final Config config) {
        this.configs.remove(config);
        this.stringConfigs.remove(config);
    }

}
