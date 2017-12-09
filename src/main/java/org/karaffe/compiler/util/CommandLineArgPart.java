package org.karaffe.compiler.util;

import java.util.Optional;

public class CommandLineArgPart {
    private final String arg;

    public CommandLineArgPart(String arg) {
        this.arg = arg;
    }

    public boolean isFullArg() {
        return arg.startsWith("--");
    }

    public boolean isShortArg() {
        return arg.startsWith("-") && !arg.startsWith("--");
    }

    public boolean isValueConfigurator() {
        return arg.contains("=") && arg.split("=").length == 2;
    }

    public boolean isFlagConfigrator() {
        if (arg.contains("=")) {
            String[] cmdValue = arg.split("=");
            if (cmdValue.length != 2) {
                return false;
            }
            String cmd = cmdValue[0];
            String value = cmdValue[1];
            return value.matches("(true|false)");
        }
        return true;
    }

    public String getCmd() {
        String cmdP = arg.split("=")[0];
        if (cmdP.startsWith("--")) {
            return cmdP.substring(2);
        } else if (cmdP.startsWith("-")) {
            return cmdP.substring(1);
        }
        return cmdP;
    }

    public Optional<String> getValue() {
        String[] cmdValue = arg.split("=");
        if (cmdValue.length != 2) {
            return Optional.empty();
        }
        return Optional.of(cmdValue[1]);
    }

    public void mapToFlagConfig() {

    }

    public boolean isValidFormat() {
        return arg.matches("-(-)?([\\w\\.]+)(=(\\w+))?");
    }

    @Override
    public String toString() {
        return this.arg;
    }
}
