package org.karaffe.compiler;

import karaffe.core.Console;
import karaffe.core.Int;
import net.nokok.azm.ClassWriter;
import net.nokok.azm.MethodVisitor;
import net.nokok.azm.Opcodes;
import net.nokok.azm.Type;
import net.nokok.azm.tree.AbstractInsnNode;
import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeBaseVisitor;
import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeParser;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
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
        MethodResolver resolver = new MethodResolver(Console.class);
        Method method = resolver.getCompatibleMethod("println", typeStack.pop()).orElseThrow(IllegalStateException::new);
        methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, Type.getInternalName(Console.class), "println", Type.getMethodDescriptor(method), false);
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
            Class<?> param = typeStack.pop();
            Class<?> owner = typeStack.pop();
            if (!owner.equals(param)) {
                context.addOutputText(String.format("[ERROR]'%s'+'%s' is not applicable", owner.getName(), param.getName()));
                typeStack.push(Object.class);
                return context;
            }
            OperatorResolver operatorResolver = new OperatorResolver(owner);
            operatorResolver.plus(param).accept(methodVisitor);
            typeStack.push(owner);
        } else {
            throw new IllegalStateException(ctx.op.getText());
        }
        return context;
    }

    @Override
    public CompilerContext visitLiteral(KaraffeParser.LiteralContext ctx) {
        if (ctx.IntegerLiteral() != null) {
            int i = Integer.parseInt(ctx.getText());
            methodVisitor.visitTypeInsn(Opcodes.NEW, Type.getInternalName(Int.class));
            methodVisitor.visitInsn(Opcodes.DUP);
            ConstructorResolver constructorResolver = new ConstructorResolver(Int.class);
            Constructor<?> constructor = constructorResolver.getConstructor(int.class).orElseThrow(IllegalStateException::new);
            AbstractInsnNode abstractInsnNode = BytecodeSelectorForNumber.fromInt(i);
            abstractInsnNode.accept(methodVisitor);
            methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, Type.getInternalName(Int.class), "<init>", Type.getConstructorDescriptor(constructor), false);
            typeStack.push(Int.class);

        } else if (ctx.StringLiteral() != null) {
            typeStack.push(karaffe.core.String.class);
            methodVisitor.visitLdcInsn(ctx.getText().substring(1, ctx.getText().length() - 1));
        } else {
            throw new IllegalStateException(ctx.toString());
        }
        return context;
    }
}
