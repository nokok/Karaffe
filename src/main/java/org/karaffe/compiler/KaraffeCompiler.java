package org.karaffe.compiler;

import net.nokok.azm.ClassWriter;
import net.nokok.azm.Opcodes;
import net.nokok.azm.Type;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeBaseVisitor;
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
            this.context.addOutputs("Usage:");
            this.context.addOutputs("  krfc <options> <sources>");
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
            CharStream charStream;
            if (source.hasFilePath()) {
                throw new UnsupportedOperationException("Not implemented");
            } else {
                charStream = CharStreams.fromString(source.toString());
            }
            KaraffeLexer lexer = new KaraffeLexer(charStream);
            KaraffeParser parser = new KaraffeParser(new CommonTokenStream(lexer));
            KaraffeParser.CompilationUnitContext compilationUnitContext = parser.compilationUnit();
            compilationUnitContext.accept(new KaraffeBaseVisitor<Void>() {
                @Override
                public Void visitClassDef(KaraffeParser.ClassDefContext ctx) {
                    ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
                    classWriter.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, ctx.Identifier().getText(), null, Type.getInternalName(Object.class), null);
                    classWriter.visitEnd();
                    byte[] bytes = classWriter.toByteArray();
                    context.addOutputFile(Paths.get(ctx.Identifier().getText() + ".class"), bytes);
                    return super.visitClassDef(ctx);
                }
            });
        }
        for (Map.Entry<Path, byte[]> entry : context.getOutputFiles().entrySet()) {
            try {
                Files.write(entry.getKey(), entry.getValue());
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }
    }

    public String out() {
        return context.getOutputText();
    }
}
