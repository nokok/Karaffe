package org.karaffe.compiler;

import java.io.File;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

import org.karaffe.compiler.lexer.KaraffeLexer;
import org.karaffe.compiler.parser.KaraffeParser;
import org.karaffe.compiler.parser.util.MatchResult;
import org.karaffe.compiler.parser.util.MatchResult.Failure;
import org.karaffe.compiler.parser.util.MatchResult.Success;
import org.karaffe.compiler.phases.LexerPhase;
import org.karaffe.compiler.phases.ParserPhase;
import org.karaffe.compiler.phases.PhaseRunner;
import org.karaffe.compiler.phases.SetupPhase;
import org.karaffe.compiler.phases.Transformer;
import org.karaffe.compiler.tree.CompileUnit;
import org.karaffe.compiler.util.Traceable;

public class Main implements Traceable {

    private final String[] args;

    public Main(final String[] args) {
        this.args = args;
    }

    public static void main(final String[] args) throws Exception {
        final Main main = new Main(args);
        System.exit(main.run());
    }

    public static String usage() {
        return "";
    }

    public int run() throws Exception {
        final Transformer<String[], CompilerContext> setup = new SetupPhase();
        final Optional<CompilerContext> cOpt = setup.transform(this.args);
        if (!cOpt.isPresent()) {
            System.out.println(Main.usage());
            return -1;
        }
        final CompilerContext context = cOpt.get();
        final LexerPhase lexerPhase = new LexerPhase(context);
        final ParserPhase parserPhase = new ParserPhase(context);

        context.sourceStream()
                .map(PhaseRunner.first(lexerPhase))
                .map(PhaseRunner.after(parserPhase));

        final List<String> lines = Files.readAllLines(new File(this.args[0]).toPath());
        final String source = lines.stream().reduce((l1, l2) -> l1 + "\n" + l2).get();

        final KaraffeParser parser = new KaraffeParser();
        final MatchResult result = parser.parse(new KaraffeLexer(source).run());

        Traceable.INTERNAL_LOGGER.debug("Parse OK ? : " + result.isSuccess());
        if (result.isFailure()) {
            final Failure failure = result.toFailure().get();
            this.error(failure.toString());
        }

        final Success success = result.toSuccess().get();
        final CompileUnit compileUnit = (CompileUnit) success.getNode().orElse(null);
        this.debug(compileUnit.toString());
        return 0;
    }
}