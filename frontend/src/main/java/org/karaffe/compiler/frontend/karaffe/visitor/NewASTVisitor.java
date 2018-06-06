package org.karaffe.compiler.frontend.karaffe.visitor;

import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.karaffe.compiler.base.Errors;
import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.Trees;
import org.karaffe.compiler.base.tree.def.Def;
import org.karaffe.compiler.base.tree.def.Defs;
import org.karaffe.compiler.base.tree.expr.Exprs;
import org.karaffe.compiler.base.tree.expr.Operators;
import org.karaffe.compiler.base.tree.term.Terms;
import org.karaffe.compiler.frontend.karaffe.antlrautogenerated.KaraffeBaseVisitor;
import org.karaffe.compiler.frontend.karaffe.antlrautogenerated.KaraffeParser;
import org.karaffe.compiler.frontend.karaffe.exceptions.KaraffeSemaException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class NewASTVisitor extends KaraffeBaseVisitor<Tree> implements PositionContext {

    @Override
    public Tree visitCompilationUnit(KaraffeParser.CompilationUnitContext ctx) {
        Tree.CompilationUnit compilationUnit = Trees.compilationUnit();
        compilationUnit.setPos(Position.of(ctx.getStart()));
        List<KaraffeParser.TopLevelStatementContext> topLevelStatementContexts = ctx.topLevelStatement();
        for (KaraffeParser.TopLevelStatementContext topLevelStatementContext : topLevelStatementContexts) {
            compilationUnit.addTopLevel(topLevelStatementContext.accept(this));
        }
        return compilationUnit;
    }

    @Override
    public Tree visitTopLevelStatement(KaraffeParser.TopLevelStatementContext ctx) {
        KaraffeParser.ClassDefContext classDefContext = ctx.classDef();
        if (classDefContext == null) {
            throw new NullPointerException();
        }
        return classDefContext.accept(this);
    }

    @Override
    public Tree visitClassDef(KaraffeParser.ClassDefContext ctx) {
        KaraffeParser.SimpleClassDefContext simpleClassDefContext = ctx.simpleClassDef();
        if (simpleClassDefContext == null) {
            throw new NullPointerException();
        }
        return simpleClassDefContext.accept(this);
    }

    @Override
    public Tree visitSimpleClassDef(KaraffeParser.SimpleClassDefContext ctx) {
        TerminalNode identifier = ctx.Identifier();
        Def c = Defs.classDef(identifier.getText());
        c.setPos(getPosition(ctx.CLASS()));
        KaraffeParser.ClassDefBodyContext classDefBodyContext = ctx.classDefBody();
        c.addBody(classDefBodyContext.accept(this));
        return c;
    }

    @Override
    public Tree visitClassDefBody(KaraffeParser.ClassDefBodyContext ctx) {
        Tree.Template template = Trees.template();
        KaraffeParser.ClassDefMemberListContext memberListContext = ctx.classDefMemberList();
        KaraffeParser.ClassDefMemberContext innerMemberContext = memberListContext.classDefMember();
        while (innerMemberContext != null) {
            template.addChild(innerMemberContext.accept(this));
            if (memberListContext == null) {
                break;
            }
            memberListContext = memberListContext.classDefMemberList();
            if (memberListContext == null) {
                break;
            }
            innerMemberContext = memberListContext.classDefMember();
        }
        return template;
    }

    @Override
    public Tree visitClassDefMemberList(KaraffeParser.ClassDefMemberListContext ctx) {
        if (ctx.classDefMemberList() != null) {
            return ctx.classDefMemberList().accept(this);
        }
        return ctx.classDefMember().accept(this);
    }

    @Override
    public Tree visitClassDefMember(KaraffeParser.ClassDefMemberContext ctx) {
        KaraffeParser.MainMethodDefContext mainMethodDefContext = ctx.mainMethodDef();
        if (mainMethodDefContext == null) {
            throw new NullPointerException();
        }
        return mainMethodDefContext.accept(this);
    }

    @Override
    public Tree visitMainMethodDef(KaraffeParser.MainMethodDefContext ctx) {
        Def mainMethodDef = Defs.mainMethodDef();
        if (ctx.statementBlock() != null) {
            mainMethodDef.addBody(ctx.statementBlock().accept(this));
        }
        return mainMethodDef;
    }

    @Override
    public Tree visitStatementBlock(KaraffeParser.StatementBlockContext ctx) {
        if (ctx.statementList() != null) {
            return ctx.statementList().accept(this);
        }
        return ctx.statement().accept(this);
    }

    @Override
    public Tree visitStatementList(KaraffeParser.StatementListContext ctx) {
        List<Tree> stmtList = new ArrayList<>();
        stmtList.add(Objects.requireNonNull(ctx.statement().accept(this)));
        if (ctx.statementList() != null) {
            stmtList.addAll(ctx.statementList().accept(this).getChildren());
        }
        return Exprs.block(stmtList);
    }

    @Override
    public Tree visitStatement(KaraffeParser.StatementContext ctx) {
        if (ctx.exprStmt != null) {
            return ctx.exprStmt.accept(this);
        }
        if (ctx.letStmt != null) {
            return Defs.letDef(
                    Terms.varName(ctx.name.getText()),
                    Terms.typeName(ctx.typeName.getText()),
                    ctx.expr() == null ? Terms.emptyTree() : ctx.expr().accept(this)
            );
        }
        if (ctx.assignTarget != null) {
            return Defs.assignment(
                    Terms.varName(ctx.assignTarget.getText()),
                    ctx.expr().accept(this)
            );
        }
        throw new IllegalStateException();
    }

    @Override
    public Tree visitExpr(KaraffeParser.ExprContext ctx) {
        if (ctx.ifExpr != null) {
            return Exprs.ifExpr(
                    ctx.cond.accept(this),
                    ctx.then.accept(this),
                    ctx.el == null ? Terms.emptyTree() : ctx.el.accept(this)
            );
        }
        if (ctx.loop != null) {
            return Exprs.whileExpr(
                    ctx.cond.accept(this),
                    ctx.body.accept(this)
            );
        }
        if (ctx.block != null) {
            List<Tree> trees = ctx.statement().stream().map(c -> c.accept(this)).collect(Collectors.toList());
            return Exprs.block(trees);
        }
        if (ctx.simple != null) {
            return ctx.simple.accept(this);
        }
        throw new IllegalStateException();
    }

    @Override
    public Tree visitSimpleExpr(KaraffeParser.SimpleExprContext ctx) {
        if (ctx.exprList() != null) {
            return ctx.exprList().accept(this);
        }
        throw new IllegalStateException();
    }

    @Override
    public Tree visitExprList(KaraffeParser.ExprListContext ctx) {
        if (ctx.op == null) {
            return ctx.rangeExpr().accept(this);
        }
        return Exprs.apply(
                ctx.left.accept(this),
                Operators.byToken(ctx.op),
                ctx.right.accept(this)
        );
    }

    @Override
    public Tree visitRangeExpr(KaraffeParser.RangeExprContext ctx) {
        if (ctx.op == null) {
            return ctx.orExpr().accept(this);
        }
        return Exprs.apply(
                ctx.left.accept(this),
                Operators.byToken(ctx.op),
                ctx.right.accept(this)
        );
    }

    @Override
    public Tree visitOrExpr(KaraffeParser.OrExprContext ctx) {
        if (ctx.op == null) {
            return ctx.andExpr().accept(this);
        }
        return Exprs.apply(
                ctx.left.accept(this),
                Operators.byToken(ctx.op),
                ctx.right.accept(this)
        );
    }

    @Override
    public Tree visitAndExpr(KaraffeParser.AndExprContext ctx) {
        if (ctx.op == null) {
            return ctx.equalityExpr().accept(this);
        }
        return Exprs.apply(
                ctx.left.accept(this),
                Operators.byToken(ctx.op),
                ctx.right.accept(this)
        );
    }

    @Override
    public Tree visitEqualityExpr(KaraffeParser.EqualityExprContext ctx) {
        if (ctx.op == null) {
            return ctx.conditionalExpr().accept(this);
        }
        return Exprs.apply(
                ctx.left.accept(this),
                Operators.byToken(ctx.op),
                ctx.right.accept(this)
        );
    }

    @Override
    public Tree visitConditionalExpr(KaraffeParser.ConditionalExprContext ctx) {
        if (ctx.op == null) {
            return ctx.additiveExpr().accept(this);
        }
        return Exprs.apply(
                ctx.left.accept(this),
                Operators.byToken(ctx.op),
                ctx.right.accept(this)
        );
    }

    @Override
    public Tree visitAdditiveExpr(KaraffeParser.AdditiveExprContext ctx) {
        if (ctx.op == null) {
            return ctx.multiplicativeExpr().accept(this);
        }
        return Exprs.apply(
                ctx.left.accept(this),
                Operators.byToken(ctx.op),
                ctx.right.accept(this)
        );
    }

    @Override
    public Tree visitMultiplicativeExpr(KaraffeParser.MultiplicativeExprContext ctx) {
        if (ctx.op == null) {
            return ctx.powExpr().accept(this);
        }
        return Exprs.apply(
                ctx.left.accept(this),
                Operators.byToken(ctx.op),
                ctx.right.accept(this)
        );
    }

    @Override
    public Tree visitPowExpr(KaraffeParser.PowExprContext ctx) {
        if (ctx.op == null) {
            return ctx.unaryExpr().accept(this);
        }
        return Exprs.apply(
                ctx.left.accept(this),
                Operators.byToken(ctx.op),
                ctx.right.accept(this)
        );
    }

    @Override
    public Tree visitUnaryExpr(KaraffeParser.UnaryExprContext ctx) {
        if (ctx.op == null) {
            return ctx.primary().accept(this);
        }
        return Exprs.unaryApply(
                Operators.byToken(ctx.op),
                ctx.exp.accept(this)
        );
    }

    @Override
    public Tree visitCast(KaraffeParser.CastContext ctx) {
        return Exprs.cast(
                ctx.castTarget.accept(this),
                Terms.typeName(ctx.typeName.getText())
        );
    }

    @Override
    public Tree visitObjectMethodInvocation(KaraffeParser.ObjectMethodInvocationContext ctx) {
        return Exprs.apply(
                ctx.owner.accept(this),
                Terms.varName(ctx.methodName.getText()),
                ctx.args.accept(this)
        );
    }

    @Override
    public Tree visitLit(KaraffeParser.LitContext ctx) {
        return ctx.literal().accept(this);
    }

    @Override
    public Tree visitNestedExpr(KaraffeParser.NestedExprContext ctx) {
        return ctx.expr().accept(this);
    }

    @Override
    public Tree visitNewInstance(KaraffeParser.NewInstanceContext ctx) {
        return Exprs.newInstance(
                Terms.typeName(ctx.typeName.getText()),
                ctx.args.accept(this)
        );
    }

    @Override
    public Tree visitExprName(KaraffeParser.ExprNameContext ctx) {
        return Terms.varName(ctx.exprName.getText());
    }

    @Override
    public Tree visitLocalMethodInvocation(KaraffeParser.LocalMethodInvocationContext ctx) {
        return Exprs.apply(
                Terms.emptyTree(),
                Terms.varName(ctx.methodName.getText()),
                ctx.args.accept(this)
        );
    }

    @Override
    public Tree visitLiteral(KaraffeParser.LiteralContext ctx) {
        TerminalNode integerLiteral = ctx.IntegerLiteral();
        if (integerLiteral != null) {
            return Exprs.intValue(integerLiteral);
        }
        TerminalNode stringLiteral = ctx.StringLiteral();
        if (stringLiteral != null) {
            return Exprs.stringValue(stringLiteral);
        }
        throw new IllegalStateException();
    }

    @Override
    public Tree visitErrorNode(ErrorNode node) {
        Errors.syntaxError(Position.of(node.getSymbol()), "");
        throw new KaraffeSemaException();
    }
}
