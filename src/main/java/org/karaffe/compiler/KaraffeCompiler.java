package org.karaffe.compiler;

import org.antlr.v4.runtime.CommonTokenStream;
import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeLexer;
import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeParser;
import org.karaffe.compiler.util.KaraffeSource;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class KaraffeCompiler {
    private final CompilerContext context;

    public KaraffeCompiler() {
        this(new CompilerContext());
    }

    public KaraffeCompiler(CompilerContext context) {
        this.context = context;
    }

    public void run() {
        if (this.context.requireShowUsage()) {
            this.context.addOutputText("Usage:");
            this.context.addOutputText("  krfc <options> <sources>");
            return;
        }
        for (String arg : context.getRawArgs()) {
            if (arg.endsWith(".krf")) {
                try {
                    this.context.addSource(KaraffeSource.fromPath(Paths.get(arg)));
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            }
        }
        List<KaraffeSource> sources = context.getSources();
        for (KaraffeSource source : sources) {
            if (source.hasFilePath()) {
                throw new UnsupportedOperationException("Not implemented");
            }
            KaraffeLexer lexer = new KaraffeLexer(source.asCharStream());
            lexer.removeErrorListeners();
            DefaultErrorListener errorHandler = new DefaultErrorListener(context);
            lexer.addErrorListener(errorHandler);
            KaraffeParser parser = new KaraffeParser(new CommonTokenStream(lexer));
            parser.removeErrorListeners();
            parser.addErrorListener(errorHandler);
            KaraffeParser.CompilationUnitContext compilationUnitContext = parser.compilationUnit();

            if (errorHandler.hasSyntaxError()) {
                continue;
            }

            compilationUnitContext.accept(new KaraffeParserVisitor(context));
        }
        if (!this.context.hasFlag("dry-run")) {
            for (Map.Entry<Path, byte[]> entry : context.getOutputFiles().entrySet()) {
                try {
                    Files.write(entry.getKey(), entry.getValue());
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            }
        }
    }

    public String out() {
        return context.getOutputText();
    }
}
