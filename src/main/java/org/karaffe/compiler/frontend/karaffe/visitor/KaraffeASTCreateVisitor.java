package org.karaffe.compiler.frontend.karaffe.visitor;

import org.antlr.v4.runtime.tree.TerminalNode;
import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeBaseVisitor;
import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeParser;
import org.karaffe.compiler.tree.NodeType;
import org.karaffe.compiler.tree.Tree;
import org.karaffe.compiler.tree.TreeFactory;
import org.karaffe.compiler.util.CompilerContext;
import org.karaffe.compiler.util.Position;

import java.util.List;

import static org.karaffe.compiler.tree.NodeType.Argument;
import static org.karaffe.compiler.tree.NodeType.Arguments;
import static org.karaffe.compiler.tree.NodeType.Binding;
import static org.karaffe.compiler.tree.NodeType.Body;
import static org.karaffe.compiler.tree.NodeType.Identifier;
import static org.karaffe.compiler.tree.NodeType.Modifier;
import static org.karaffe.compiler.tree.NodeType.Modifiers;
import static org.karaffe.compiler.tree.NodeType.Parameters;
import static org.karaffe.compiler.tree.NodeType.TypeName;
import static org.karaffe.compiler.tree.NodeType.VarName;

public class KaraffeASTCreateVisitor extends KaraffeBaseVisitor<Tree> {

  private final CompilerContext context;
  private final Tree compilationUnit;
  private Tree moduleTree = TreeFactory.newTree(NodeType.Module, Position.noPos());
  private Tree packageTree = TreeFactory.newTree(NodeType.Package, Position.noPos());

  public KaraffeASTCreateVisitor(CompilerContext context) {
    this.context = context;
    this.compilationUnit = TreeFactory.newTree(NodeType.CompilationUnit, Position.noPos());
  }

  public Tree getCompilationUnit() {
    moduleTree.addChild(packageTree);
    this.compilationUnit.addChild(moduleTree);
    return compilationUnit;
  }

  @Override
  public Tree visitSourceFile(KaraffeParser.SourceFileContext ctx) {
    Tree tree = TreeFactory.newTree(NodeType.SourceFile, new Position(ctx));
    String sourceName = ctx.EOF().getSymbol().getInputStream().getSourceName();
    tree.addChild(TreeFactory.newTree(Identifier, sourceName, new Position(ctx)));
    for (KaraffeParser.ClassDefContext classDefContext : ctx.classDef()) {
      Tree contextTree = classDefContext.accept(this);
      tree.addChild(contextTree);
    }
    this.packageTree.addChild(tree);
    return tree;
  }

  @Override
  public Tree visitClassDef(KaraffeParser.ClassDefContext ctx) {
    Tree tree = TreeFactory.newTree(NodeType.DefClass, new Position(ctx));
    TerminalNode identifier = ctx.Identifier();
    if (identifier == null) {
      return TreeFactory.newTree(NodeType.Error);
    }
    tree.addChild(TreeFactory.newTree(Identifier, identifier.getText(), new Position(identifier.getSymbol())));
    Tree superClass = TreeFactory.newTree(NodeType.SuperClass, new Position(ctx));
    superClass.addChild(TreeFactory.newTree(NodeType.TypeName, "Object", new Position(ctx)));
    tree.addChild(superClass);
    Tree modifiers = TreeFactory.newTree(NodeType.Modifiers, new Position(ctx));
    modifiers.addChild(TreeFactory.newTree(NodeType.Modifier, "public", new Position(ctx)));
    tree.addChild(modifiers);
    Tree body = TreeFactory.newTree(NodeType.Body, new Position(ctx));
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
      return TreeFactory.newTree(NodeType.This, new Position(ctx.t));
    } else if (ctx.left != null) {
      Tree tmpApply = TreeFactory.newTree(NodeType.FlatApply, new Position(ctx));
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
      Tree select = TreeFactory.newTree(NodeType.Select, new Position(ctx));
      select.addChild(TreeFactory.newTree(NodeType.Identifier, name, new Position(ctx.name)));
      select.addChild(targetTree);
      return select;
    } else if (ctx.function != null) {
      Tree apply = TreeFactory.newTree(NodeType.Apply, new Position(ctx));
      apply.addChild(ctx.function.accept(this));
      Tree arguments = TreeFactory.newTree(Arguments, new Position(ctx));
      if (ctx.args != null) {
        for (KaraffeParser.ExprContext exprContext : ctx.args.expr()) {
          Tree arg = TreeFactory.newTree(Argument, new Position(exprContext));
          arg.addChild(exprContext.accept(this));
          arguments.addChild(arg);
        }
      }
      apply.addChild(arguments);
      return apply;
    } else if (ctx.id != null) {
      Tree id = TreeFactory.newTree(VarName, ctx.id.getText(), new Position(ctx));
      return id;
    } else {
      throw new IllegalStateException();
    }
  }

  @Override

  public Tree visitEntryPointBlock(KaraffeParser.EntryPointBlockContext ctx) {
    Tree tree = TreeFactory.newTree(NodeType.DefMethod, new Position(ctx));
    tree.addChild(TreeFactory.newTree(Identifier, "main", new Position(ctx)));
    Tree modifiers = TreeFactory.newTree(NodeType.Modifiers, new Position(ctx));
    modifiers.addChild(TreeFactory.newTree(NodeType.Modifier, "public", new Position(ctx)));
    modifiers.addChild(TreeFactory.newTree(NodeType.Modifier, "static", new Position(ctx)));
    tree.addChild(modifiers);
    Tree returnType = TreeFactory.newTree(NodeType.ReturnType, new Position(ctx));
    returnType.addChild(TreeFactory.newTree(NodeType.TypeName, "Unit", new Position(ctx)));
    tree.addChild(returnType);
    Tree parameters = TreeFactory.newTree(NodeType.Parameters, new Position(ctx));
    Tree args = TreeFactory.newTree(NodeType.Parameter, new Position(ctx));
    args.addChild(TreeFactory.newTree(Identifier, "args", new Position(ctx)));
    args.addChild(TreeFactory.newTree(NodeType.ArrayTypeName, "java.lang.String", new Position(ctx)));
    parameters.addChild(args);
    tree.addChild(parameters);
    List<KaraffeParser.StatementContext> statements = ctx.statement();
    Tree body = TreeFactory.newTree(NodeType.Body, new Position(ctx));
    for (KaraffeParser.StatementContext statement : statements) {
      body.addChild(statement.accept(this));
    }
    tree.addChild(body);
    return tree;
  }

  @Override
  public Tree visitVarDef(KaraffeParser.VarDefContext ctx) {
    Tree tree = TreeFactory.newTree(NodeType.DefVar, new Position(ctx));
    Tree binding = ctx.binding().accept(this);
    binding.dig(Identifier).ifPresent(tree::addChild);
    binding.dig(TypeName).ifPresent(tree::addChild);
    if (ctx.expr() != null) {
      Tree body = TreeFactory.newTree(Body, new Position(ctx.expr()));
      body.addChild(ctx.expr().accept(this));
      tree.addChild(body);
    }
    return tree;
  }

  public Tree visitLiteral(KaraffeParser.LiteralContext ctx) {
    Tree tree;
    if (ctx.IntegerLiteral() != null) {
      tree = TreeFactory.newTree(NodeType.IntLiteral, ctx.IntegerLiteral().getText(), new Position(ctx));
    } else if (ctx.StringLiteral() != null) {
      tree = TreeFactory.newTree(NodeType.StringLiteral, ctx.StringLiteral().getText(), new Position(ctx));
//            tree.addAttribute(new InferredType(String.class));
    } else {
      throw new IllegalArgumentException();
    }
    return tree;
  }

  @Override
  public Tree visitExprList(KaraffeParser.ExprListContext ctx) {
    return TreeFactory.newTree(NodeType.Error, new Position(ctx));
  }

  @Override
  public Tree visitInitBlock(KaraffeParser.InitBlockContext ctx) {
    Tree constructor = TreeFactory.newTree(NodeType.DefConstructor, new Position(ctx));
    Tree modifiers = TreeFactory.newTree(Modifiers, new Position(ctx));
    modifiers.addChild(TreeFactory.newTree(Modifier, "public"));
    Tree parameters = TreeFactory.newTree(Parameters, new Position(ctx));
    Tree body = TreeFactory.newTree(Body, new Position(ctx));
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
    Tree assign = TreeFactory.newTree(NodeType.Assign, new Position(ctx));
    assign.addChild(ctx.target.accept(this));
    assign.addChild(ctx.initializer.accept(this));
    return assign;
  }

  @Override
  public Tree visitBinaryOperator(KaraffeParser.BinaryOperatorContext ctx) {
    Tree op = TreeFactory.newTree(NodeType.BinOp, ctx.getText(), new Position(ctx));
    return op;
  }

  @Override
  public Tree visitBinding(KaraffeParser.BindingContext ctx) {
    Tree tree = TreeFactory.newTree(Binding, new Position(ctx));
    tree.addChild(TreeFactory.newTree(Identifier, ctx.Identifier().getText(), new Position(ctx.Identifier().getSymbol())));
    tree.addChild(ctx.typeName().accept(this));
    return tree;
  }

  @Override
  public Tree visitTypeName(KaraffeParser.TypeNameContext ctx) {
    String typeName = ctx.getText();
    Tree tree = TreeFactory.newTree(TypeName, typeName, new Position(ctx));
    return tree;
  }
}
