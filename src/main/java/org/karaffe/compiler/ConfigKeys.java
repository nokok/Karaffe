package org.karaffe.compiler;

import java.util.Optional;
import java.util.stream.Stream;

public class ConfigKeys {

    public static Optional<FlagConfigs> valueOf(final String cmd) {
        return Stream.of(FlagConfigs.values()).filter(c -> c.is(cmd)).findFirst();
    }

    public enum FlagConfigs {
        SHOW_VERSION("v", "version");

        private final String shortArg;
        private final String[] aliases;

        private FlagConfigs(final String shortArg, final String... aliases) {
            this.shortArg = shortArg;
            this.aliases = aliases;
        }

        public boolean is(final String cmd) {
            if (this.shortArg.equals(cmd)) {
                return true;
            }
            for (final String alias : this.aliases) {
                if (alias.equals(cmd)) {
                    return true;
                }
            }
            return false;
        }
    }
}
