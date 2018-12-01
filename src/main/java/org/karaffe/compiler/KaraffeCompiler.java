package org.karaffe.compiler;

import org.antlr.v4.runtime.CommonTokenStream;
import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeLexer;
import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeParser;
import org.karaffe.compiler.util.KaraffeSource;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class KaraffeCompiler {
    private final CompilerContext context;

    public KaraffeCompiler(CompilerContext context) {
        this.context = context;
    }

    public void run() {
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
            parser.addParseListener(new WarningListener(context));
            KaraffeParser.CompilationUnitContext compilationUnitContext = parser.compilationUnit();

            if (errorHandler.hasSyntaxError()) {
                continue;
            }

            try {
                compilationUnitContext.accept(new KaraffeParserVisitor(context));
            } catch (KaraffeCompilerRuntimeException e) {
                context.addOutputText(e.getMessage());
                continue;
            }
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

}
