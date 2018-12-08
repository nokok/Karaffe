package org.karaffe.compiler.visitor;

import karaffe.core.Console;
import karaffe.core.Int;
import org.karaffe.compiler.SemanticAnalysisException;
import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeBaseVisitor;
import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeParser;
import org.karaffe.compiler.gen.BytecodeSupport;
import org.karaffe.compiler.resolver.MethodResolver;
import org.karaffe.compiler.util.BytecodeEntry;
import org.karaffe.compiler.util.CompilerContext;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Stack;

public class KaraffeParserVisitor extends KaraffeBaseVisitor<CompilerContext> {
    private BytecodeSupport bytecodeSupport = new BytecodeSupport();
    private CompilerContext context;
    private MethodVisitor methodVisitor = null;
    private Stack<Class<?>> typeStack = new Stack<>();

    public KaraffeParserVisitor(CompilerContext context) {
        this.context = Objects.requireNonNull(context);
    }

    @Override
    public CompilerContext visitClassDef(KaraffeParser.ClassDefContext ctx) {
        bytecodeSupport.newClassDefinition(ctx.Identifier().getText());
        super.visitClassDef(ctx);
        BytecodeEntry bytecodeEntry = bytecodeSupport.closeThisClass();
        context.addOutput(bytecodeEntry);
        return context;
    }

    @Override
    public CompilerContext visitEntryPointBlock(KaraffeParser.EntryPointBlockContext ctx) {
        bytecodeSupport.startMainMethod();
        super.visitEntryPointBlock(ctx);
        bytecodeSupport.endMethod();
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
        Class<?> param = typeStack.pop();
        Class<?> owner = typeStack.pop();
        String sourceName = ctx.op.getInputStream().getSourceName();
        if (!owner.equals(param)) {
            String msg = String.format("[ERROR]'%s'+'%s' is not applicable at %s:%s in %s", owner.getName(), param.getName(), ctx.op.getLine(), ctx.op.getCharPositionInLine(), sourceName);
            throw new SemanticAnalysisException(msg);
        }
        OperatorResolver operatorResolver = new OperatorResolver(owner);
        if (ctx.op.getText().equals("+")) {
            operatorResolver.plus(param).accept(methodVisitor);
            typeStack.push(owner);
        } else if (ctx.op.getText().equals("-")) {
            operatorResolver.minus(param).accept(methodVisitor);
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
            bytecodeSupport.pushIntLiteral(i);
        } else if (ctx.StringLiteral() != null) {
            String value = ctx.getText().substring(1, ctx.getText().length() - 1);
            bytecodeSupport.pushStringLiteral(value);
        } else {
            throw new IllegalStateException(ctx.toString());
        }
        return context;
    }
}
