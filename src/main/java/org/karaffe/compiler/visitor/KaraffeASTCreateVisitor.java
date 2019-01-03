package org.karaffe.compiler.visitor;

import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeBaseVisitor;
import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeParser;
import org.karaffe.compiler.tree.AnonymousTree;
import org.karaffe.compiler.tree.NodeType;
import org.karaffe.compiler.tree.Tree;
import org.karaffe.compiler.util.CompilerContext;
import org.karaffe.compiler.util.Position;

import java.util.List;

public class KaraffeASTCreateVisitor extends KaraffeBaseVisitor<Tree> {

    private final CompilerContext context;
    private final Tree compilationUnit;

    public KaraffeASTCreateVisitor(CompilerContext context) {
        this.context = context;
        this.compilationUnit = new AnonymousTree(NodeType.CompilationUnit, Position.noPos());
    }

    public Tree getCompilationUnit() {
        return compilationUnit;
    }

    @Override
    public Tree visitSourceFile(KaraffeParser.SourceFileContext ctx) {
        Tree tree = new Tree(NodeType.SourceFile, ctx.EOF().getSymbol().getInputStream().getSourceName(), new Position(ctx));
        for (KaraffeParser.ClassDefContext classDefContext : ctx.classDef()) {
            tree.addChild(classDefContext.accept(this));
        }
        this.compilationUnit.addChild(tree);
        return tree;
    }

    @Override
    public Tree visitClassDef(KaraffeParser.ClassDefContext ctx) {
        Tree tree = new Tree(NodeType.DefClass, ctx.Identifier().getText(), new Position(ctx));
        Tree superClass = new AnonymousTree(NodeType.SuperClass, new Position(ctx));
        superClass.addChild(new Tree(NodeType.TypeName, "java.lang.Object", new Position(ctx)));
        tree.addChild(superClass);
        Tree modifiers = new AnonymousTree(NodeType.Modifiers, new Position(ctx));
        modifiers.addChild(new Tree(NodeType.Modifier, "public", new Position(ctx)));
        tree.addChild(modifiers);
        Tree body = new AnonymousTree(NodeType.Body, new Position(ctx));
        if (ctx.typeDefBody() != null) {
            List<KaraffeParser.StatementContext> statements = ctx.typeDefBody().statement();
            for (KaraffeParser.StatementContext statement : statements) {
                body.addChild(statement.accept(this));
            }
        }
        tree.addChild(body);
        super.visitClassDef(ctx);
        return tree;
    }

    @Override
    public Tree visitExpr(KaraffeParser.ExprContext ctx) {
        if (ctx.lit != null) {
            //literal
            return ctx.literal().accept(this);
        } else if (ctx.t != null) {
            return new AnonymousTree(NodeType.This, new Position(ctx.t));
        } else if (ctx.op != null) {
            Tree tree = new Tree(NodeType.Apply, "()", new Position(ctx));
            Tree operator = new AnonymousTree(NodeType.Select, new Position(ctx.op));
            operator.addChild(new Tree(NodeType.Identifier, ctx.op.getText(), new Position(ctx.op)));
            tree.addChild(operator);
            operator.addChild(ctx.left.accept(this));
            operator.addChild(ctx.right.accept(this));
            return tree;
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public Tree visitEntryPointBlock(KaraffeParser.EntryPointBlockContext ctx) {
        Tree tree = new Tree(NodeType.DefMethod, "main", new Position(ctx));
        Tree modifiers = new AnonymousTree(NodeType.Modifiers, new Position(ctx));
        modifiers.addChild(new Tree(NodeType.Modifier, "public", new Position(ctx)));
        modifiers.addChild(new Tree(NodeType.Modifier, "static", new Position(ctx)));
        tree.addChild(modifiers);
        Tree returnType = new AnonymousTree(NodeType.ReturnType, new Position(ctx));
        returnType.addChild(new Tree(NodeType.TypeName, "void", new Position(ctx)));
        tree.addChild(returnType);
        Tree parameters = new AnonymousTree(NodeType.Parameters, new Position(ctx));
        Tree args = new Tree(NodeType.Parameter, "args", new Position(ctx));
        args.addChild(new Tree(NodeType.TypeName, String[].class.getCanonicalName(), new Position(ctx)));
        parameters.addChild(args);
        tree.addChild(parameters);
        List<KaraffeParser.StatementContext> statements = ctx.statement();
        Tree body = new AnonymousTree(NodeType.Body, new Position(ctx));
        for (KaraffeParser.StatementContext statement : statements) {
            body.addChild(statement.accept(this));
        }
        tree.addChild(body);
        return tree;
    }

    @Override
    public Tree visitVarDef(KaraffeParser.VarDefContext ctx) {
        Tree tree = new Tree(NodeType.DefVar, ctx.Identifier().getText(), new Position(ctx));
        return tree;
    }

    @Override
    public Tree visitPrintFunction(KaraffeParser.PrintFunctionContext ctx) {
        Tree tree = new Tree(NodeType.Apply, "()", new Position(ctx));
        Tree select = new AnonymousTree(NodeType.Select, new Position(ctx));
        select.addChild(new Tree(NodeType.Identifier, "print", new Position(ctx.PRINT().getSymbol())));
        tree.addChild(select);
        if (ctx.body != null) {
            select.addChild(ctx.body.accept(this));
        }
        return tree;
    }

    public Tree visitLiteral(KaraffeParser.LiteralContext ctx) {
        Tree tree;
        if (ctx.IntegerLiteral() != null) {
            tree = new Tree(NodeType.IntLiteral, ctx.IntegerLiteral().getText(), new Position(ctx));
        } else if (ctx.StringLiteral() != null) {
            tree = new Tree(NodeType.StringLiteral, ctx.StringLiteral().getText(), new Position(ctx));
//            tree.addAttribute(new InferredType(String.class));
        } else {
            throw new IllegalArgumentException();
        }
        return tree;
    }
}
