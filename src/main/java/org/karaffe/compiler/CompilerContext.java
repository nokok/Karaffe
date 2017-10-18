package org.karaffe.compiler;

import java.io.File;
import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Stream;

import org.karaffe.compiler.ConfigKeys.FlagConfigs;
import org.karaffe.compiler.util.SourceRepository;

public class CompilerContext {

    private final SourceRepository sourceRepository;
    private final Map<ConfigKeys.FlagConfigs, Boolean> configs;

    public CompilerContext() {
        this(new SourceRepository());
    }

    public CompilerContext(final SourceRepository sourceRepository) {
        this.sourceRepository = sourceRepository;
        this.configs = new EnumMap<>(ConfigKeys.FlagConfigs.class);
    }

    public void add(final File f) {
        this.sourceRepository.add(f);
    }

    public void add(final FlagConfigs config) {
        this.configs.put(config, true);
    }

    public void remove(final FlagConfigs config) {
        this.configs.remove(config);
    }

    public Stream<File> sourceStream() {
        return this.sourceRepository.sourceStream();
    }

}
