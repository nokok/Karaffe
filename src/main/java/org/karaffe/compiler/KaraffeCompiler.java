package org.karaffe.compiler;

import net.nokok.azm.ClassWriter;
import net.nokok.azm.MethodVisitor;
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
            CharStream charStream;
            if (source.hasFilePath()) {
                throw new UnsupportedOperationException("Not implemented");
            } else {
                charStream = CharStreams.fromString(source.toString());
            }
            KaraffeLexer lexer = new KaraffeLexer(charStream);
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

            compilationUnitContext.accept(new KaraffeBaseVisitor<Void>() {

                ClassWriter classWriter = null;

                @Override
                public Void visitClassDef(KaraffeParser.ClassDefContext ctx) {
                    classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
                    classWriter.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, ctx.Identifier().getText(), null, Type.getInternalName(Object.class), null);
                    super.visitClassDef(ctx);
                    classWriter.visitEnd();
                    byte[] bytes = classWriter.toByteArray();
                    context.addOutputFile(Paths.get(ctx.Identifier().getText() + ".class"), bytes);
                    return null;
                }

                @Override
                public Void visitEntryPointBlock(KaraffeParser.EntryPointBlockContext ctx) {
                    MethodVisitor methodVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "main", Type.getMethodDescriptor(Type.VOID_TYPE, Type.getType(String[].class)), null, null);
                    methodVisitor.visitInsn(Opcodes.RETURN);
                    methodVisitor.visitEnd();
                    return super.visitEntryPointBlock(ctx);
                }
            });
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
