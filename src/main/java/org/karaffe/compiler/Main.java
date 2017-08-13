package org.karaffe.compiler;

import java.io.File;
import java.nio.file.Files;
import java.util.List;
import java.util.function.Predicate;

import org.karaffe.compiler.constraints.CmdLineOptionValidator;
import org.karaffe.compiler.lexer.KaraffeLexer;
import org.karaffe.compiler.parser.KaraffeParser;
import org.karaffe.compiler.parser.util.MatchResult;
import org.karaffe.compiler.parser.util.MatchResult.Failure;
import org.karaffe.compiler.parser.util.MatchResult.Success;
import org.karaffe.compiler.tree.CompileUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    private final String[] args;
    private final Predicate<String[]> argsValidator = new CmdLineOptionValidator();

    public Main(final String[] args) {
        this.args = args;
        if (!this.argsValidator.test(args)) {
            throw new RuntimeException();
        }
    }

    public static void main(final String[] args) throws Exception {
        final Main main = new Main(args);
        System.exit(main.run());
    }

    public int run() throws Exception {

        final List<String> lines = Files.readAllLines(new File(this.args[0]).toPath());
        final String source = lines.stream().reduce((l1, l2) -> l1 + "\n" + l2).get();

        final KaraffeParser parser = new KaraffeParser();
        final MatchResult result = parser.parse(new KaraffeLexer(source).run());

        Main.LOGGER.debug("Parse OK ? : " + result.isSuccess());
        if (result.isFailure()) {
            final Failure failure = result.toFailure().get();
            Main.LOGGER.error(failure.toString());
        }

        final Success success = result.toSuccess().get();
        final CompileUnit compileUnit = (CompileUnit) success.getNode().orElse(null);
        Main.LOGGER.debug(compileUnit.toString());
        return 0;
    }
}