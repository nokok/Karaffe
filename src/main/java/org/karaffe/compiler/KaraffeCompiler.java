package org.karaffe.compiler;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Callable;

import org.karaffe.compiler.context.SourceStream;
import org.karaffe.compiler.phases.FileLexerPhase;
import org.karaffe.compiler.phases.GenByteCodePhase;
import org.karaffe.compiler.phases.ParserPhase;
import org.karaffe.compiler.phases.PhaseRunner;
import org.karaffe.compiler.phases.ReportPhase;
import org.karaffe.compiler.phases.SetupPhase;
import org.karaffe.compiler.tree.CompileUnit;
import org.karaffe.compiler.util.ProgressTraceable;
import org.karaffe.compiler.util.Traceable;

public class KaraffeCompiler implements Callable<Integer>, Traceable, ProgressTraceable {

    private String[] commandlineArgs;

    public KaraffeCompiler(String[] commandlineArgs) {
        this.commandlineArgs = Objects.requireNonNull(commandlineArgs);
    }

    @Override
    public Integer call() throws Exception {
        this.progress("Launching Karaffe Compiler...");

        return new SetupPhase().transform(this.commandlineArgs).map(config -> {
            final SourceStream stream = SourceStream.createSourceStream(this.commandlineArgs);

            Optional<CompileUnit> compileUnitOpt = stream.sourceStream()
                    .map(PhaseRunner.first(new FileLexerPhase(config)))
                    .map(PhaseRunner.after(new ParserPhase(config)))
                    .map(PhaseRunner.after(new ReportPhase(config)))
                    .map(PhaseRunner.after(new GenByteCodePhase(config)))
                    .findFirst()
                    .flatMap(f -> f);
            compileUnitOpt.ifPresent(compileUnit -> {
                this.debug(compileUnit.toString());
            });
            this.progress("Terminating...");
            return 0;
        }).orElse(-1);
    }

}
