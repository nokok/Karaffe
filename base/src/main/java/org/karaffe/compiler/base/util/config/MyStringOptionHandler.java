package org.karaffe.compiler.base.util.config;

import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.OptionDef;
import org.kohsuke.args4j.spi.Setter;
import org.kohsuke.args4j.spi.StringOptionHandler;

public class MyStringOptionHandler extends StringOptionHandler {
    public MyStringOptionHandler(CmdLineParser parser, OptionDef option, Setter<? super String> setter) {
        super(parser, option, setter);
    }

    @Override
    public String printDefaultValue() {
        return null;
    }
}
