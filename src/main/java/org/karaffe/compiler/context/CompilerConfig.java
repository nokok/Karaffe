package org.karaffe.compiler.context;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.karaffe.compiler.context.ConfigKeys.Config;
import org.karaffe.compiler.context.ConfigKeys.FlagConfigs;
import org.karaffe.compiler.context.ConfigKeys.StringConfigs;
import org.karaffe.compiler.exceptions.IllegalFormatOptionException;
import org.karaffe.compiler.exceptions.UnrecognizedOptionException;
import org.karaffe.compiler.util.CommandLineArgPart;

public interface CompilerConfig {

    public Optional<Boolean> getFlag(ConfigKeys.FlagConfigs flagName);

    public Optional<String> getValue(ConfigKeys.StringConfigs configName);

    public default PrintStream getOutput() {
        return System.out;
    }

    public static CompilerConfig defaultConfig() {
        return new CompilerConfigImpl();
    }

    public static CompilerConfig buildConfig(String[] args) throws UnrecognizedOptionException, IllegalFormatOptionException {
        return buildConfig(Arrays.asList(args).stream().map(CommandLineArgPart::new).collect(Collectors.toList()));
    }

    public static CompilerConfig buildConfig(List<CommandLineArgPart> argParts) throws UnrecognizedOptionException, IllegalFormatOptionException {
        CompilerConfigImpl compilerConfig = new CompilerConfigImpl();

        List<FlagConfigs> flagConfigs = Arrays.asList(FlagConfigs.values());
        List<StringConfigs> stringConfigs = Arrays.asList(StringConfigs.values());

        for (CommandLineArgPart arg : argParts) {
            String cmd = arg.getCmd();
            if (!arg.isValidFormat()) {
                throw new IllegalFormatOptionException(arg.toString());
            }
            Optional<FlagConfigs> flagConfig = flagConfigs.parallelStream().filter(c -> c.is(cmd)).findFirst();
            Optional<StringConfigs> stringConfig = stringConfigs.parallelStream().filter(c -> c.is(cmd)).findFirst();

            if (!flagConfig.isPresent() && !stringConfig.isPresent()) {
                throw new UnrecognizedOptionException(arg.toString());
            }
            if (flagConfig.isPresent() && stringConfig.isPresent()) {
                throw new IllegalFormatOptionException(arg.toString());
            }

            if (arg.isValueConfigurator()) {
                String value = arg.getValue().get();
                flagConfig.ifPresent(config -> {
                    compilerConfig.set(config, Boolean.parseBoolean(value));
                });
                stringConfig.ifPresent(config -> {
                    compilerConfig.set(config, value);
                });
                continue;
            }
            flagConfig.ifPresent(compilerConfig::add);
            stringConfig.ifPresent(compilerConfig::add);
        }
        return compilerConfig;
    }
}

interface MutableCompilerConfig extends CompilerConfig {
    public void add(final Config config);

    public void add(final FlagConfigs config);

    public void set(final StringConfigs config, final String value);

    public void set(final FlagConfigs config, final boolean value);

    public void remove(final Config config);
}

class CompilerConfigImpl implements MutableCompilerConfig {

    private final Map<ConfigKeys.FlagConfigs, Boolean> flags;
    private final Map<ConfigKeys.StringConfigs, String> stringConfigs;

    public CompilerConfigImpl() {
        this.flags = new EnumMap<>(ConfigKeys.FlagConfigs.class);
        this.stringConfigs = new EnumMap<>(ConfigKeys.StringConfigs.class);
    }

    @Override
    public void add(final FlagConfigs config) {
        if (this.flags.containsKey(config)) {
            this.flags.put(config, !this.flags.get(config));
        } else {
            this.flags.put(config, Boolean.TRUE);
        }
    }

    @Override
    public void set(final FlagConfigs config, final boolean value) {
        this.flags.put(config, value);
    }

    @Override
    public void set(final StringConfigs config, final String value) {
        this.stringConfigs.put(config, Objects.requireNonNull(value));
    }

    @Override
    public void remove(final Config config) {
        this.flags.remove(config);
        this.stringConfigs.remove(config);
    }

    @Override
    public Optional<Boolean> getFlag(ConfigKeys.FlagConfigs flagName) {
        return Optional.ofNullable(this.flags.get(flagName));
    }

    @Override
    public Optional<String> getValue(ConfigKeys.StringConfigs configName) {
        return Optional.ofNullable(this.stringConfigs.get(configName));
    }

    @Override
    public void add(Config config) {
        if (config instanceof FlagConfigs) {
            this.add((FlagConfigs) config);
        }
    }

}
