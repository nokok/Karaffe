package org.karaffe.compiler;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.karaffe.compiler.context.CompilerConfig;
import org.karaffe.compiler.context.SourceStream;
import org.karaffe.compiler.phases.GenByteCodePhase;
import org.karaffe.compiler.phases.LexerPhase;
import org.karaffe.compiler.phases.ParserPhase;
import org.karaffe.compiler.phases.PhaseRunner;
import org.karaffe.compiler.phases.ReportPhase;
import org.karaffe.compiler.phases.SetupPhase;
import org.karaffe.compiler.phases.Transformer;
import org.karaffe.compiler.tree.CompileUnit;
import org.karaffe.compiler.util.ProgressTraceable;
import org.karaffe.compiler.util.SourceCodeContainer;
import org.karaffe.compiler.util.Traceable;

public class KaraffeCompiler implements Callable<Integer>, Traceable, ProgressTraceable {

    private String[] commandlineArgs;

    public KaraffeCompiler(String[] commandlineArgs) {
        this.commandlineArgs = commandlineArgs;
    }

    private SourceStream createSourceStream(String[] args) {
        List<File> sourceList = Stream.of(args).filter(arg -> arg.endsWith(".krf")).map(File::new).collect(Collectors.toList());
        Set<File> sourceSet = new HashSet<>(sourceList);
        if (sourceList.size() != sourceSet.size()) {
            this.error("source duplicated");
        }
        SourceCodeContainer container = new SourceCodeContainer(sourceSet);
        return container;
    }

    @Override
    public Integer call() throws Exception {
        this.progress("Launching Karaffe Compiler...");
        final Transformer<String[], CompilerConfig> setup = new SetupPhase();
        final Optional<CompilerConfig> cOpt = setup.transform(this.commandlineArgs);
        if (!cOpt.isPresent()) {
            this.progress(Main.usage());
            return -1;
        }

        CompilerConfig context = cOpt.get();
        final LexerPhase lexerPhase = new LexerPhase(context);
        final ParserPhase parserPhase = new ParserPhase(context);
        final ReportPhase reportPhase = new ReportPhase(context);
        final GenByteCodePhase genByteCode = new GenByteCodePhase(context);

        final SourceStream stream = createSourceStream(this.commandlineArgs);
        final Optional<CompileUnit> mayBeCompileUnit = stream.sourceStream()
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
