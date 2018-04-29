package org.karaffe.compiler.launcher.config;


import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.OptionDef;
import org.kohsuke.args4j.spi.BooleanOptionHandler;
import org.kohsuke.args4j.spi.Setter;

public class MyBooleanOptionHandler extends BooleanOptionHandler {
    public MyBooleanOptionHandler(CmdLineParser parser, OptionDef option, Setter<Boolean> setter) {
        super(parser, option, setter);
    }
    @Override
    public String printDefaultValue() {
        return null;
    }
}