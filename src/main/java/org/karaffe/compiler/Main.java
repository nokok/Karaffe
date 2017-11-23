package org.karaffe.compiler;

import java.util.Optional;

import org.karaffe.compiler.phases.GenByteCodePhase;
import org.karaffe.compiler.phases.LexerPhase;
import org.karaffe.compiler.phases.ParserPhase;
import org.karaffe.compiler.phases.PhaseRunner;
import org.karaffe.compiler.phases.ReportPhase;
import org.karaffe.compiler.phases.SetupPhase;
import org.karaffe.compiler.phases.Transformer;
import org.karaffe.compiler.tree.CompileUnit;
import org.karaffe.compiler.util.ProgressTraceable;
import org.karaffe.compiler.util.Traceable;

public class Main implements Traceable, ProgressTraceable {

    private final String[] args;

    public Main(final String[] args) {
        this.args = args;
    }

    public static void main(final String[] args) throws Exception {
        final Main main = new Main(args);
        System.exit(main.run());
    }

    public static String usage() {
        return "Usage not available";
    }

    public int run() throws Exception {
        this.progress("Launching Karaffe Compiler...");
        final Transformer<String[], CompilerContext> setup = new SetupPhase();
        final Optional<CompilerContext> cOpt = setup.transform(this.args);
        if (!cOpt.isPresent()) {
            this.progress(Main.usage());
            return -1;
        }
        final CompilerContext context = cOpt.get();
        final LexerPhase lexerPhase = new LexerPhase(context);
        final ParserPhase parserPhase = new ParserPhase(context);
        final ReportPhase reportPhase = new ReportPhase(context);
        final GenByteCodePhase genByteCode = new GenByteCodePhase(context);

        final Optional<CompileUnit> mayBeCompileUnit = context.sourceStream()
                .map(PhaseRunner.first(lexerPhase))
                .map(PhaseRunner.after(parserPhase))
                .map(PhaseRunner.after(reportPhase))
                .map(PhaseRunner.after(genByteCode))
                .findFirst()
                .flatMap(f -> f);

        mayBeCompileUnit.ifPresent(compileUnit -> {
            this.debug(compileUnit.toString());
        });

        this.progress("Terminating...");
        return 0;
    }
}