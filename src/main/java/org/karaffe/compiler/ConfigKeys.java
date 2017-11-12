package org.karaffe.compiler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public final class ConfigKeys {

    public static Optional<Config> valueOf(final String cmd) {
        final List<Config> configs = new ArrayList<>();
        configs.addAll(Arrays.asList(FlagConfigs.values()));
        configs.addAll(Arrays.asList(StringConfigs.values()));
        return configs.parallelStream().filter(c -> c.is(cmd)).findFirst();
    }

    public static interface Config {
        public String getShortArgs();

        public List<String> getAliases();

        public default boolean is(final String cmd) {
            if (this.getShortArgs().equals(cmd)) {
                return true;
            }
            for (final String alias : this.getAliases()) {
                if (alias.equals(cmd)) {
                    return true;
                }
            }
            return false;
        }
    }

    public static enum FlagConfigs implements Config {
        SHOW_VERSION("v", "version");

        private final String shortArg;
        private final String[] aliases;

        private FlagConfigs(final String shortArg, final String... aliases) {
            this.shortArg = shortArg;
            this.aliases = aliases;
        }

        @Override
        public String getShortArgs() {
            return this.shortArg;
        }

        @Override
        public List<String> getAliases() {
            return new ArrayList<>(Arrays.asList(this.aliases));
        }

    }

    public static enum StringConfigs implements Config {
        LOG_LEVEL("logLevel");

        private final String shortArg;
        private final String[] aliases;

        private StringConfigs(final String shortArg, final String... aliases) {
            this.shortArg = shortArg;
            this.aliases = aliases;
        }

        @Override
        public String getShortArgs() {
            return this.shortArg;
        }

        @Override
        public List<String> getAliases() {
            return new ArrayList<>(Arrays.asList(this.aliases));
        }
    }
}
