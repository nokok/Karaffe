package org.karaffe.compiler;

import karaffe.core.Console;
import net.nokok.azm.ClassWriter;
import net.nokok.azm.MethodVisitor;
import net.nokok.azm.Opcodes;
import net.nokok.azm.Type;
import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeBaseVisitor;
import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeParser;

import java.nio.file.Paths;
import java.util.Objects;
import java.util.Stack;

public class KaraffeParserVisitor extends KaraffeBaseVisitor<CompilerContext> {
    private CompilerContext context;
    private ClassWriter classWriter = null;
    private MethodVisitor methodVisitor = null;
    private Stack<Class<?>> typeStack = new Stack<>();

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
        return context;
    }

    @Override
    public CompilerContext visitPrintFunction(KaraffeParser.PrintFunctionContext ctx) {
        super.visitPrintFunction(ctx);
        if (ctx.body == null) {
            typeStack.push(String.class);
            methodVisitor.visitLdcInsn("");
        }
        methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, Type.getInternalName(Console.class), "println", Type.getMethodDescriptor(Type.VOID_TYPE, Type.getType(typeStack.pop())), false);
        return context;
    }

    @Override
    public CompilerContext visitAdditiveExpr(KaraffeParser.AdditiveExprContext ctx) {
        if (ctx.op == null) {
            return super.visitAdditiveExpr(ctx);
        }
        ctx.left.accept(this);
        ctx.right.accept(this);
        if (ctx.op.getText().equals("+")) {
            methodVisitor.visitInsn(Opcodes.IADD);
            typeStack.push(int.class);
        } else {
            throw new IllegalStateException(ctx.op.getText());
        }
        return context;
    }

    @Override
    public CompilerContext visitLiteral(KaraffeParser.LiteralContext ctx) {
        if (ctx.IntegerLiteral() != null) {
            int i = Integer.parseInt(ctx.getText());
            if (-1 <= i && i <= 5) {
                typeStack.push(int.class);
                switch (i) {
                case -1:
                    methodVisitor.visitInsn(Opcodes.ICONST_M1);
                    break;
                case 0:
                    methodVisitor.visitInsn(Opcodes.ICONST_0);
                    break;
                case 1:
                    methodVisitor.visitInsn(Opcodes.ICONST_1);
                    break;
                case 2:
                    methodVisitor.visitInsn(Opcodes.ICONST_2);
                    break;
                case 3:
                    methodVisitor.visitInsn(Opcodes.ICONST_3);
                    break;
                case 4:
                    methodVisitor.visitInsn(Opcodes.ICONST_4);
                    break;
                case 5:
                    methodVisitor.visitInsn(Opcodes.ICONST_5);
                    break;
                }
            } else if (Byte.MIN_VALUE < i && i < Byte.MAX_VALUE) {
                typeStack.push(byte.class);
                methodVisitor.visitIntInsn(Opcodes.BIPUSH, i);
            } else if (Short.MIN_VALUE < i && i < Short.MAX_VALUE) {
                typeStack.push(short.class);
                methodVisitor.visitIntInsn(Opcodes.SIPUSH, i);
            } else {
                typeStack.push(int.class);
                methodVisitor.visitLdcInsn(i);
            }
        } else if (ctx.StringLiteral() != null) {
            typeStack.push(String.class);
            methodVisitor.visitLdcInsn(ctx.getText().substring(1, ctx.getText().length() - 1));
        } else {
            throw new IllegalStateException(ctx.toString());
        }
        return context;
    }
}
