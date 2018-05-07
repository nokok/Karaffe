package org.karaffe.compiler.frontend.karaffe.tasks;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.frontend.karaffe.transformer.util.Result;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.ParserProperties;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class ParseCommandLineOptionsTask implements Task {

    @Override
    public String name() {
        return "cmd";
    }

    @Override
    public String description() {
        return "Parse command line options";
    }

    @Override
    public Result run(CompilerContext context) {
        ParserProperties properties = ParserProperties.defaults().withUsageWidth(999);
        CmdLineParser cmdLineParser = new CmdLineParser(context.cmdLineOptions, properties);
        context.setCommandLineParserProperties(properties);
        context.setCommandLineParser(cmdLineParser);

        if (context.getArgs().length == 0) {
            context.setInvalidArg();
            return Result.WITH_WARN;
        }
        try {
            cmdLineParser.parseArgument(context.getArgs());
            List<String> unrecognizedOptions =
                    context.cmdLineOptions.arguments
                            .stream()
                            .filter(arg -> !Files.exists(Paths.get(arg)))
                            .collect(Collectors.toList());

            if (!unrecognizedOptions.isEmpty()) {
                context.setInvalidArg();
            }
            return Result.SUCCESS;
        } catch (CmdLineException e) {
            context.setInvalidArg();
            return Result.WITH_WARN;
        }
    }

    @Override
    public boolean isRunnable(CompilerContext context) {
        return true;
    }

    @Override
    public boolean changed() {
        return true;
    }
}
