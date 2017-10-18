package org.karaffe.compiler.phases;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

import org.karaffe.compiler.CompilerContext;
import org.karaffe.compiler.lexer.KaraffeLexer;
import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.util.Traceable;

public class LexerPhase extends AbstractTransformer<File, Tokens> implements Traceable {

    private final CompilerContext context;

    public LexerPhase(final CompilerContext context) {
        super(File.class, Tokens.class);
        this.context = context;
        this.context.toString();
    }

    @Override
    public Optional<Tokens> transform(final File source) {
        final String fileName = source.toPath().toString();
        try {
            final String code = Files.readAllLines(source.toPath()).stream().reduce((l1, l2) -> l1 + "\n" + l2).get();
            final Tokens tokens = new Tokens(new KaraffeLexer(fileName, code).run());
            return Optional.of(tokens);
        } catch (final IOException e) {
            this.error("file not found.", e);
            return Optional.empty();
        }
    }

    @Override
    public boolean checkPreCondition(final File input) {
        if (input.isDirectory()) {
            return false;
        }
        if (!input.exists()) {
            return false;
        }
        if (!input.canRead()) {
            return false;
        }
        return true;
    }

}
