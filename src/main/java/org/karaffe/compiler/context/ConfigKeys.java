package org.karaffe.compiler.context;

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
        public String getShortArg();

        public List<String> getAliases();

        public default boolean is(final String cmd) {
            if (cmd.startsWith("-")) {
                throw new IllegalArgumentException("- can not be included. : " + cmd);
            }
            String s = cmd;
            if (this.getShortArg().toLowerCase().equals(s.toLowerCase())) {
                return true;
            }
            for (final String alias : this.getAliases()) {
                if (alias.toLowerCase().equals(s.toLowerCase())) {
                    return true;
                }
            }
            return false;
        }
    }

    public static enum FlagConfigs implements Config {
        SHOW_VERSION("v", "version"),

        COMPILER_INSERT_EOF("compiler.insertEOF"),
        COMPILER_AUTO_CRLF_TO_LF("compiler.autoCRLFtoLF"),
        ;

        private final String shortArg;
        private final String[] aliases;

        private FlagConfigs(final String shortArg, final String... aliases) {
            this.shortArg = shortArg;
            this.aliases = aliases;
        }

        @Override
        public String getShortArg() {
            return this.shortArg;
        }

        @Override
        public List<String> getAliases() {
            return new ArrayList<>(Arrays.asList(this.aliases));
        }

    }

    public static enum StringConfigs implements Config {
        LOG_LEVEL("logLevel"),
        ;

        private final String shortArg;
        private final String[] aliases;

        private StringConfigs(final String shortArg, final String... aliases) {
            this.shortArg = shortArg;
            this.aliases = aliases;
        }

        @Override
        public String getShortArg() {
            return this.shortArg;
        }

        @Override
        public List<String> getAliases() {
            return new ArrayList<>(Arrays.asList(this.aliases));
        }
    }
}
