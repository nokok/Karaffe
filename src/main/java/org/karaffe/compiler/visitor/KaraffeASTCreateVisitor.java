package org.karaffe.compiler.visitor;

import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeBaseVisitor;
import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeParser;
import org.karaffe.compiler.tree.NodeType;
import org.karaffe.compiler.tree.Tree;
import org.karaffe.compiler.tree.attr.Attribute;
import org.karaffe.compiler.tree.attr.AttributeType;
import org.karaffe.compiler.util.CompilerContext;
import org.karaffe.compiler.util.Position;

import java.util.List;

public class KaraffeASTCreateVisitor extends KaraffeBaseVisitor<Tree> {

    private final CompilerContext context;
    private final Tree compilationUnit;

    public KaraffeASTCreateVisitor(CompilerContext context) {
        this.context = context;
        this.compilationUnit = new Tree(NodeType.CompilationUnit, "", null);
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
//        tree.addAttribute(new SuperClass() /*java.lang.Object*/);
//        tree.addAttribute(new ModifierAttribute(JavaModifier.PUBLIC));
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
    public Tree visitExpr(KaraffeParser.ExprContext ctx) {
        if (ctx.lit != null) {
            //literal
            return ctx.literal().accept(this);
        } else if (ctx.t != null) {
            return new Tree(NodeType.This, "this", new Position(ctx.t));
        } else if (ctx.op != null) {
            Tree tree = new Tree(NodeType.Apply, "()", new Position(ctx));
            Tree operator = new Tree(NodeType.Select, ctx.op.getText(), new Position(ctx.op));
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
        Attribute attribute = new Attribute(AttributeType.Modifier);
        Attribute methodSignature = new Attribute(AttributeType.MethodSignature);
        tree.addAttribute(attribute);
        tree.addAttribute(methodSignature);
        List<KaraffeParser.StatementContext> statements = ctx.statement();
        for (KaraffeParser.StatementContext statement : statements) {
            tree.addChild(statement.accept(this));
        }
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
        Tree select = new Tree(NodeType.Select, "print", new Position(ctx.PRINT().getSymbol()));
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
//            tree.addAttribute(new InferredType(int.class));
        } else if (ctx.StringLiteral() != null) {
            tree = new Tree(NodeType.StringLiteral, ctx.StringLiteral().getText(), new Position(ctx));
//            tree.addAttribute(new InferredType(String.class));
        } else {
            throw new IllegalArgumentException();
        }
        return tree;
    }
}
