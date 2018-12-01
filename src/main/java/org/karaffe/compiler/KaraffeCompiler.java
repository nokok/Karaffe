package org.karaffe.compiler;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeLexer;
import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeParser;
import org.karaffe.compiler.util.CompilerContext;
import org.karaffe.compiler.util.KaraffeSource;
import org.karaffe.compiler.visitor.KaraffeParserVisitor;
import org.karaffe.compiler.visitor.WarningVisitor;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class KaraffeCompiler {
    private final CompilerContext context;

    public KaraffeCompiler(CompilerContext context) {
        this.context = context;
    }

    public void run() {
        List<KaraffeSource> sources = context.getSources();
        for (KaraffeSource source : sources) {
            try {
                Optional<KaraffeParser.CompilationUnitContext> optParseContext = parse(source);
                KaraffeParserVisitor parserVisitor = new KaraffeParserVisitor(context);
                WarningVisitor warningVisitor = new WarningVisitor(context);
                optParseContext.ifPresent(c -> c.accept(parserVisitor));
                optParseContext.ifPresent(c -> c.accept(warningVisitor));
            } catch (KaraffeCompilerRuntimeException e) {
                context.addOutputText(e.getMessage());
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

    private Optional<KaraffeParser.CompilationUnitContext> parse(KaraffeSource source) {
        try {
            KaraffeLexer lexer = new KaraffeLexer(source.asCharStream());
            lexer.removeErrorListeners();
            DefaultErrorListener errorHandler = new DefaultErrorListener(context);
            lexer.addErrorListener(errorHandler);
            CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);
            KaraffeParser parser = new KaraffeParser(commonTokenStream);
            parser.setErrorHandler(new KaraffeParseErrorStrategy());
            parser.removeErrorListeners();
            parser.addErrorListener(errorHandler);
            if (errorHandler.hasSyntaxError()) {
                return Optional.empty();
            } else {
                return Optional.of(parser.compilationUnit());
            }
        } catch (RecognitionException e) {
            return Optional.empty();
        }
    }

}
