package org.karaffe.compiler.util;

import java.util.Optional;

public class CommandLineArgPart {
    private final String arg;

    public CommandLineArgPart(final String arg) {
        this.arg = arg;
    }

    public String getCmd() {
        final String cmdP = this.arg.split("=")[0];
        if (cmdP.startsWith("--")) {
            return cmdP.substring(2);
        } else if (cmdP.startsWith("-")) {
            return cmdP.substring(1);
        }
        return cmdP;
    }

    public Optional<String> getValue() {
        final String[] cmdValue = this.arg.split("=");
        if (cmdValue.length != 2) {
            return Optional.empty();
        }
        return Optional.of(cmdValue[1]);
    }

    public boolean isFlagConfigrator() {
        if (this.arg.contains("=")) {
            final String[] cmdValue = this.arg.split("=");
            if (cmdValue.length != 2) {
                return false;
            }
            final String cmd = cmdValue[0];
            final String value = cmdValue[1];
            return value.matches("(true|false)");
        }
        return true;
    }

    public boolean isFullArg() {
        return this.arg.startsWith("--");
    }

    public boolean isShortArg() {
        return this.arg.startsWith("-") && !this.arg.startsWith("--");
    }

    public boolean isValidFormat() {
        return this.arg.matches("-(-)?([\\w\\.]+)(=(\\w+))?");
    }

    public boolean isValueConfigurator() {
        return this.arg.contains("=") && (this.arg.split("=").length == 2);
    }

    public void mapToFlagConfig() {

    }

    @Override
    public String toString() {
        return this.arg;
    }
}
