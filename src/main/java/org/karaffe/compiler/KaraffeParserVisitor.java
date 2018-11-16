package org.karaffe.compiler;

import net.nokok.azm.ClassWriter;
import net.nokok.azm.MethodVisitor;
import net.nokok.azm.Opcodes;
import net.nokok.azm.Type;
import org.antlr.v4.runtime.Token;
import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeBaseVisitor;
import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeParser;

import java.io.PrintStream;
import java.nio.file.Paths;
import java.util.Objects;

public class KaraffeParserVisitor extends KaraffeBaseVisitor<CompilerContext> {
    private CompilerContext context;
    private ClassWriter classWriter = null;
    private MethodVisitor methodVisitor = null;

    KaraffeParserVisitor(CompilerContext context) {
        this.context = Objects.requireNonNull(context);
    }

    @Override
    public CompilerContext visitClassDef(KaraffeParser.ClassDefContext ctx) {
        classWriter = new ClassWriter(net.nokok.azm.ClassWriter.COMPUTE_FRAMES);
        classWriter.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, ctx.Identifier().getText(), null, Type.getInternalName(Object.class), null);
        super.visitClassDef(ctx);
        classWriter.visitEnd();
        byte[] bytes = classWriter.toByteArray();
        context.addOutputFile(Paths.get(ctx.Identifier().getText() + ".class"), bytes);
        return context;
    }

    @Override
    public CompilerContext visitEntryPointBlock(KaraffeParser.EntryPointBlockContext ctx) {
        methodVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "main", Type.getMethodDescriptor(Type.VOID_TYPE, Type.getType(String[].class)), null, null);
        super.visitEntryPointBlock(ctx);
        methodVisitor.visitInsn(Opcodes.RETURN);
        methodVisitor.visitMaxs(0, 0);
        methodVisitor.visitEnd();
        return null;
    }

    @Override
    public CompilerContext visitPrintFunction(KaraffeParser.PrintFunctionContext ctx) {
        super.visitPrintFunction(ctx);
        methodVisitor.visitFieldInsn(Opcodes.GETSTATIC, Type.getInternalName(System.class), "out", Type.getDescriptor(PrintStream.class));
        if (ctx.StringLiteral() == null) {
            methodVisitor.visitLdcInsn("");
        } else {
            Token token = ctx.StringLiteral().getSymbol();
            methodVisitor.visitLdcInsn(token.getText().substring(1, token.getText().length() - 1));
        }
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, Type.getInternalName(PrintStream.class), "println", Type.getMethodDescriptor(Type.VOID_TYPE, Type.getType(String.class)), false);
        return null;
    }
}
