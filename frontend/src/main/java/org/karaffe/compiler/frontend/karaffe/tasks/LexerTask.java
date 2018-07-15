package org.karaffe.compiler.frontend.karaffe.tasks;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.task.AbstractTask;
import org.karaffe.compiler.base.task.TaskResult;
import org.karaffe.compiler.frontend.karaffe.antlrautogenerated.KaraffeLexer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringReader;

public class LexerTask extends AbstractTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(LexerTask.class);

    @Override
    public String name() {
        return "frontend-karaffe-lexing";
    }

    @Override
    public String description() {
        return "Lexical analysis";
    }

    @Override
    public TaskResult run(CompilerContext context) {
        ErrorListener errorListener = new ErrorListener(context);
        context.sourceFileStream()
                .forEach(file -> {
                    LOGGER.trace("new Lexer for : {} ", file.getFileName());
                    try {
                        CodePointCharStream codePointCharStream;
                        if (file.isUnknownFile()) {
                            codePointCharStream = CharStreams.fromString(file.toString());
                        } else {
                            codePointCharStream = CharStreams.fromReader(new StringReader(file.toString()), file.toPath().toAbsolutePath().toString());
                        }
                        KaraffeLexer lexer = new KaraffeLexer(codePointCharStream);
                        lexer.removeErrorListeners();
                        lexer.addErrorListener(errorListener);
                        context.addLexer(lexer);
                    } catch (IOException e) {
                        throw new IllegalStateException(e);
                    }
                });
        return errorListener.getResult();
    }

    @Override
    public boolean isRunnable(CompilerContext context) {
        return context.sourceFileStream().count() > 0;
    }

    @Override
    public boolean changed() {
        return false;
    }
}
