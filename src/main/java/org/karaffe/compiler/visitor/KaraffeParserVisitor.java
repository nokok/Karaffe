package org.karaffe.compiler.visitor;

import karaffe.core.Console;
import karaffe.core.Int;
import org.karaffe.compiler.SemanticAnalysisException;
import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeBaseVisitor;
import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeParser;
import org.karaffe.compiler.gen.BytecodeSupport;
import org.karaffe.compiler.report.Report;
import org.karaffe.compiler.resolver.MethodResolver;
import org.karaffe.compiler.util.BytecodeEntry;
import org.karaffe.compiler.util.CompilerContext;
import org.karaffe.compiler.util.Position;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Stack;

public class KaraffeParserVisitor extends KaraffeBaseVisitor<CompilerContext> {
    private BytecodeSupport bytecodeSupport = new BytecodeSupport();
    private CompilerContext context;
    private Stack<Class<?>> typeStack = new Stack<>();

    public KaraffeParserVisitor(CompilerContext context) {
        this.context = Objects.requireNonNull(context);
    }

    @Override
    public CompilerContext visitClassDef(KaraffeParser.ClassDefContext ctx) {
        bytecodeSupport.newClassDefinition(ctx.Identifier().getText());
        super.visitClassDef(ctx);
        BytecodeEntry bytecodeEntry = bytecodeSupport.closeThisClass();
        context.add(bytecodeEntry);
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
            bytecodeSupport.pushStringLiteral("");
            typeStack.push(String.class);
        }
        MethodResolver resolver = new MethodResolver(Console.class);
        Method method = resolver.getCompatibleMethod("println", typeStack.pop()).orElseThrow(IllegalStateException::new);
        bytecodeSupport.invokeStaticMethod(method);
        return context;
    }

    @Override
    public CompilerContext visitVarDef(KaraffeParser.VarDefContext ctx) {
        super.visitVarDef(ctx);
        String fieldName = ctx.Identifier().getText();
        bytecodeSupport.newFieldDefinition(fieldName, Int.class);
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
        if (!owner.equals(param)) {
            this.context.add(Report.newErrorReport(String.format("'%s'+'%s' is not applicable", owner.getName(), param.getName())).with(new Position(ctx.op)).build());
            throw new SemanticAnalysisException();
        }
        if (ctx.op.getText().equals("+")) {
            bytecodeSupport.applyPlusOperator(owner, param);
        } else if (ctx.op.getText().equals("-")) {
            bytecodeSupport.applyMinusOperator(owner, param);
        } else {
            throw new IllegalStateException(ctx.op.getText());
        }
        typeStack.push(owner);
        return context;
    }

    @Override
    public CompilerContext visitLiteral(KaraffeParser.LiteralContext ctx) {
        if (ctx.IntegerLiteral() != null) {
            int i = Integer.parseInt(ctx.getText());
            bytecodeSupport.pushIntLiteral(i);
            typeStack.push(Int.class);
        } else if (ctx.StringLiteral() != null) {
            String value = ctx.getText().substring(1, ctx.getText().length() - 1);
            bytecodeSupport.pushStringLiteral(value);
            typeStack.push(karaffe.core.String.class);
        } else {
            throw new IllegalStateException(ctx.toString());
        }
        return context;
    }
}
