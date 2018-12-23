package org.karaffe.compiler.visitor;

import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeBaseVisitor;
import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeParser;
import org.karaffe.compiler.tree.NodeType;
import org.karaffe.compiler.tree.Tree;
import org.karaffe.compiler.util.CompilerContext;

import java.util.List;

public class KaraffeASTCreateVisitor extends KaraffeBaseVisitor<Tree> {

    private final CompilerContext context;

    public KaraffeASTCreateVisitor(CompilerContext context) {
        this.context = context;
    }

    @Override
    public Tree visitCompilationUnit(KaraffeParser.CompilationUnitContext ctx) {
        Tree tree = new Tree(NodeType.CompilationUnit, ctx.EOF().getSymbol().getInputStream().getSourceName());
        for (KaraffeParser.ClassDefContext classDefContext : ctx.classDef()) {
            tree.addChild(classDefContext.accept(this));
        }
        return tree;
    }

    @Override
    public Tree visitClassDef(KaraffeParser.ClassDefContext ctx) {
        Tree tree = new Tree(NodeType.DefClass, ctx.Identifier().getText());
        if (ctx.typeDefBody() != null) {
            List<KaraffeParser.StatementContext> statements = ctx.typeDefBody().statement();
            for (KaraffeParser.StatementContext statement : statements) {
                tree.addChild(statement.accept(this));
            }
        }
        super.visitClassDef(ctx);
        return tree;
    }

    @Override
    public Tree visitEntryPointBlock(KaraffeParser.EntryPointBlockContext ctx) {
        Tree tree = new Tree(NodeType.DefMethod, "main");
        List<KaraffeParser.StatementContext> statements = ctx.statement();
        for (KaraffeParser.StatementContext statement : statements) {
            tree.addChild(statement.accept(this));
        }
        return tree;
    }

    @Override
    public Tree visitVarDef(KaraffeParser.VarDefContext ctx) {
        Tree tree = new Tree(NodeType.DefVar, ctx.Identifier().getText());
        return tree;
    }

    @Override
    public Tree visitPrintFunction(KaraffeParser.PrintFunctionContext ctx) {
        Tree tree = new Tree(NodeType.Apply, "()");
        tree.addChild(new Tree(NodeType.Select, "println"));
        tree.addChild(ctx.body.accept(this));
        return tree;
    }

    @Override
    public Tree visitAdditiveExpr(KaraffeParser.AdditiveExprContext ctx) {
        if (ctx.op == null) {
            return ctx.primary().accept(this);
        }
        Tree tree = new Tree(NodeType.Apply, "()");
        tree.addChild(new Tree(NodeType.Select, ctx.op.getText()));
        tree.addChild(ctx.left.accept(this));
        tree.addChild(ctx.right.accept(this));
        return tree;
    }

    @Override
    public Tree visitLiteral(KaraffeParser.LiteralContext ctx) {
        Tree tree;
        if (ctx.IntegerLiteral() != null) {
            tree = new Tree(NodeType.IntLiteral, ctx.IntegerLiteral().getText());
        } else if (ctx.StringLiteral() != null) {
            tree = new Tree(NodeType.StringLiteral, ctx.StringLiteral().getText());
        } else {
            throw new IllegalArgumentException();
        }
        return tree;
    }
}
