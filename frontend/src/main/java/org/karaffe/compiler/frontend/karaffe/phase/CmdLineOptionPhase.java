package org.karaffe.compiler.frontend.karaffe.phase;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.frontend.karaffe.transformer.util.Result;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.ParserProperties;

public class CmdLineOptionPhase {

    public Result run(String[] args) {
        CompilerContext context = CompilerContext.CONTEXT;
        ParserProperties properties = ParserProperties.defaults().withUsageWidth(999);
        CmdLineParser cmdLineParser = new CmdLineParser(context.cmdLineOptions, properties);
        try {
            cmdLineParser.parseArgument(args);
            context.setState(CmdLineOptionPhase.class);
            return Result.SUCCESS;
        } catch (CmdLineException e) {
            return Result.FAIL;
        }
    }
}
