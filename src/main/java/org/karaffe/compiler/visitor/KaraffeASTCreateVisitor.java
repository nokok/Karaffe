package org.karaffe.compiler.visitor;

import org.antlr.v4.runtime.tree.TerminalNode;
import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeBaseVisitor;
import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeParser;
import org.karaffe.compiler.tree.NodeType;
import org.karaffe.compiler.tree.Tree;
import org.karaffe.compiler.util.CompilerContext;
import org.karaffe.compiler.util.Position;

import java.util.List;

import static org.karaffe.compiler.tree.NodeType.Argument;
import static org.karaffe.compiler.tree.NodeType.Arguments;
import static org.karaffe.compiler.tree.NodeType.Body;
import static org.karaffe.compiler.tree.NodeType.Identifier;
import static org.karaffe.compiler.tree.NodeType.Modifiers;
import static org.karaffe.compiler.tree.NodeType.Parameters;

public class KaraffeASTCreateVisitor extends KaraffeBaseVisitor<Tree> {

  private final CompilerContext context;
  private final Tree compilationUnit;
  private Tree moduleTree = new Tree(NodeType.Module, Position.noPos());
  private Tree packageTree = new Tree(NodeType.Package, Position.noPos());

  public KaraffeASTCreateVisitor(CompilerContext context) {
    this.context = context;
    this.compilationUnit = new Tree(NodeType.CompilationUnit, Position.noPos());
  }

  public Tree getCompilationUnit() {
    moduleTree.addChild(packageTree);
    this.compilationUnit.addChild(moduleTree);
    return compilationUnit;
  }

  @Override
  public Tree visitSourceFile(KaraffeParser.SourceFileContext ctx) {
    Tree tree = new Tree(NodeType.SourceFile, new Position(ctx));
    String sourceName = ctx.EOF().getSymbol().getInputStream().getSourceName();
    tree.addChild(new Tree(Identifier, sourceName, new Position(ctx)));
    for (KaraffeParser.ClassDefContext classDefContext : ctx.classDef()) {
      Tree contextTree = classDefContext.accept(this);
      tree.addChild(contextTree);
    }
    this.packageTree.addChild(tree);
    return tree;
  }

  @Override
  public Tree visitClassDef(KaraffeParser.ClassDefContext ctx) {
    Tree tree = new Tree(NodeType.DefClass, new Position(ctx));
    TerminalNode identifier = ctx.Identifier();
    if (identifier == null) {
      return new Tree(NodeType.Error);
    }
    tree.addChild(new Tree(Identifier, identifier.getText(), new Position(identifier.getSymbol())));
    Tree superClass = new Tree(NodeType.SuperClass, new Position(ctx));
    superClass.addChild(new Tree(NodeType.TypeName, "java.lang.Object", new Position(ctx)));
    tree.addChild(superClass);
    Tree modifiers = new Tree(NodeType.Modifiers, new Position(ctx));
    modifiers.addChild(new Tree(NodeType.Modifier, "public", new Position(ctx)));
    tree.addChild(modifiers);
    Tree body = new Tree(NodeType.Body, new Position(ctx));
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
      return new Tree(NodeType.This, new Position(ctx.t));
    } else if (ctx.left != null) {
      Tree tmpApply = new Tree(NodeType.FlatApply, new Position(ctx));
      tmpApply.addChild(ctx.left.accept(this));
      for (KaraffeParser.OpExprContext opExprContext : ctx.opExpr()) {
        tmpApply.addChild(opExprContext.op.accept(this));
        Tree accept = opExprContext.right.accept(this);
        if (accept.getNodeType() == NodeType.FlatApply) {
          tmpApply.addAllChildren(accept.getChildren());
        } else {
          tmpApply.addChild(accept);
        }
      }
      return tmpApply;
    } else if (ctx.target != null) {
      Tree targetTree = ctx.target.accept(this);
      String name = ctx.name.getText();
      Tree select = new Tree(NodeType.Select, new Position(ctx));
      select.addChild(new Tree(NodeType.Identifier, name, new Position(ctx.name)));
      select.addChild(targetTree);
      return select;
    } else if (ctx.function != null) {
      Tree apply = new Tree(NodeType.Apply, new Position(ctx));
      apply.addChild(ctx.function.accept(this));
      Tree arguments = new Tree(Arguments, new Position(ctx));
      if (ctx.args != null) {
        for (KaraffeParser.ExprContext exprContext : ctx.args.expr()) {
          Tree arg = new Tree(Argument, new Position(exprContext));
          arg.addChild(exprContext.accept(this));
          arguments.addChild(arg);
        }
      }
      apply.addChild(arguments);
      return apply;
    } else if (ctx.id != null) {
      Tree id = new Tree(Identifier, ctx.id.getText(), new Position(ctx));
      return id;
    } else {
      throw new IllegalStateException();
    }
  }

  @Override
  public Tree visitEntryPointBlock(KaraffeParser.EntryPointBlockContext ctx) {
    Tree tree = new Tree(NodeType.DefMethod, new Position(ctx));
    tree.addChild(new Tree(Identifier, "main", new Position(ctx)));
    Tree modifiers = new Tree(NodeType.Modifiers, new Position(ctx));
    modifiers.addChild(new Tree(NodeType.Modifier, "public", new Position(ctx)));
    modifiers.addChild(new Tree(NodeType.Modifier, "static", new Position(ctx)));
    tree.addChild(modifiers);
    Tree returnType = new Tree(NodeType.ReturnType, new Position(ctx));
    returnType.addChild(new Tree(NodeType.TypeName, "void", new Position(ctx)));
    tree.addChild(returnType);
    Tree parameters = new Tree(NodeType.Parameters, new Position(ctx));
    Tree args = new Tree(NodeType.Parameter, new Position(ctx));
    args.addChild(new Tree(Identifier, "args", new Position(ctx)));
    args.addChild(new Tree(NodeType.TypeName, String[].class.getCanonicalName(), new Position(ctx)));
    parameters.addChild(args);
    tree.addChild(parameters);
    List<KaraffeParser.StatementContext> statements = ctx.statement();
    Tree body = new Tree(NodeType.Body, new Position(ctx));
    for (KaraffeParser.StatementContext statement : statements) {
      body.addChild(statement.accept(this));
    }
    tree.addChild(body);
    return tree;
  }

  @Override
  public Tree visitVarDef(KaraffeParser.VarDefContext ctx) {
    Tree tree = new Tree(NodeType.DefVar, new Position(ctx));
    tree.addChild(new Tree(Identifier, ctx.Identifier().getText(), new Position(ctx.Identifier().getSymbol())));
    return tree;
  }

  @Override
  public Tree visitPrintFunction(KaraffeParser.PrintFunctionContext ctx) {
    Tree tree = new Tree(NodeType.Apply, new Position(ctx));
    Tree select = new Tree(NodeType.Select, new Position(ctx));
    select.addChild(new Tree(NodeType.Identifier, "print", new Position(ctx.PRINT().getSymbol())));
    tree.addChild(select);
    Tree arguments;
    if (ctx.body == null) {
      arguments = new Tree(Arguments, new Position(ctx));
    } else {
      arguments = new Tree(Arguments, new Position(ctx.body));
      Tree argument = new Tree(NodeType.Argument, new Position(ctx.body));
      argument.addChild(ctx.body.accept(this));
      arguments.addChild(argument);
    }
    tree.addChild(arguments);
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

  @Override
  public Tree visitExprList(KaraffeParser.ExprListContext ctx) {
    return new Tree(NodeType.Error, new Position(ctx));
  }

  @Override
  public Tree visitInitBlock(KaraffeParser.InitBlockContext ctx) {
    Tree constructor = new Tree(NodeType.Constructor, new Position(ctx));
    Tree modifiers = new Tree(Modifiers, new Position(ctx));
    Tree parameters = new Tree(Parameters, new Position(ctx));
    Tree body = new Tree(Body, new Position(ctx));
    for (KaraffeParser.StatementContext statementContext : ctx.statement()) {
      body.addChild(statementContext.accept(this));
    }
    constructor.addChild(modifiers);
    constructor.addChild(parameters);
    constructor.addChild(body);
    return constructor;
  }

  @Override
  public Tree visitAssign(KaraffeParser.AssignContext ctx) {
    Tree assign = new Tree(NodeType.Assign, new Position(ctx));
    assign.addChild(ctx.target.accept(this));
    assign.addChild(ctx.initializer.accept(this));
    return assign;
  }

  @Override
  public Tree visitBinaryOperator(KaraffeParser.BinaryOperatorContext ctx) {
    Tree op = new Tree(NodeType.BinOp, ctx.getText(), new Position(ctx));
    return op;
  }
}
