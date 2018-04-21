package org.karaffe.compiler.launcher.config;

import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.OptionDef;
import org.kohsuke.args4j.spi.MapOptionHandler;
import org.kohsuke.args4j.spi.Setter;

import java.util.Map;

public class MyMapOptionHandler extends MapOptionHandler {
    public MyMapOptionHandler(CmdLineParser parser, OptionDef option, Setter<? super Map<?, ?>> setter) {
        super(parser, option, setter);
    }

    @Override
    public String printDefaultValue() {
        return null;
    }
}
