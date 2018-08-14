package org.karaffe.compiler.base.tree.expr;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.term.NameNode;
import org.karaffe.compiler.base.tree.term.Path;
import org.karaffe.compiler.base.tree.term.Terms;

import java.util.List;

public interface Exprs {

    static Tree intValue(TerminalNode node) {
        Tree tree = intValue(node.getText());
        tree.setPos(Position.of(node.getSymbol()));
        return tree;
    }

    static Tree intValue(String value) {
        Atom atom = new Atom(AtomKind.INTEGER);
        atom.setValue(value);
        return atom;
    }

    static Tree apply(Position position, Tree target, Path methodName, Tree arg) {
        Apply apply = new Apply();
        apply.setPos(position);
        apply.setName(methodName);
        apply.addChild(target);
        apply.addChild(arg);
        target.setParent(apply);
        arg.setParent(apply);
        return apply;
    }

    static Tree tuple(List<Tree> trees) {
        Tuple tuple = new Tuple();
        for (Tree tree : trees) {
            tuple.addChild(tree);
            tree.setParent(tuple);
        }
        return tuple;
    }

    static Tree stringValue(TerminalNode stringLiteral) {
        Atom strAtom = new Atom(AtomKind.STRING);
        strAtom.setPos(Position.of(stringLiteral.getSymbol()));
        strAtom.setValue(stringLiteral.getText());
        return strAtom;
    }

    static Tree ifExpr(Position position, Tree condition, Tree thenBlock, Tree elseBlock) {
        IfExpr ifExpr = new IfExpr();
        ifExpr.setPos(position);

        Block condBlock = new Block();
        condBlock.addChild(condition);
        ifExpr.addChild(condBlock);
        ifExpr.addChild(thenBlock);
        ifExpr.addChild(elseBlock);
        condBlock.setParent(ifExpr);
        thenBlock.setParent(ifExpr);
        elseBlock.setParent(ifExpr);
        return ifExpr;
    }

    static Tree whileExpr(Position position, Tree condition, Tree body) {
        WhileExpr whileExpr = new WhileExpr();
        whileExpr.setPos(position);
        // パーサー側の都合でBlockではなくそのままの値が来るため
        Block conditionBlock = new Block();
        conditionBlock.addChild(condition);
        whileExpr.addChild(conditionBlock);
        condition.setParent(conditionBlock);
        conditionBlock.setParent(whileExpr);

        Block bodyBlock = new Block(body);
        bodyBlock.addChild(body);
        whileExpr.addChild(bodyBlock);
        body.setParent(bodyBlock);
        bodyBlock.setParent(whileExpr);
        return whileExpr;
    }

    static Tree block(List<Tree> trees) {
        Block block = new Block();
        for (Tree tree : trees) {
            block.addChild(tree);
            tree.setParent(block);
        }
        return block;
    }

    static Tree unaryApply(Position position, Operator operator, Tree expr) {
        Apply apply = new Apply();
        apply.setPos(position);
        apply.setName(Terms.varName(operator.getPos(), operator.asFullName()));
        apply.addChild(expr);
        expr.setParent(apply);
        return apply;
    }

    static Tree cast(Position position, Tree tree, Path typeName) {
        Cast cast = new Cast();
        cast.setPos(position);
        cast.addChild(tree);
        cast.setTypeName(typeName);
        tree.setParent(cast);
        return cast;
    }

    static Tree newInstance(Position position, Path typeName, Tree args) {
        Apply apply = new Apply();
        apply.setPos(position);
        apply.setName(Terms.varName(Position.noPos(), "<init>"));
        apply.setTypeName(typeName);
        apply.addChild(args);
        args.setParent(apply);
        return apply;
    }

    static Tuple tuple() {
        return new Tuple();
    }

    static Tree id(Token exprName) {
        Atom atom = new Atom(AtomKind.IDENTIFIER);
        atom.setPos(Position.of(exprName));
        atom.setValue(exprName.getText());
        return atom;
    }

    static Tree id(Position position, String name) {
        Atom atom = new Atom(AtomKind.IDENTIFIER);
        atom.setPos(position);
        atom.setValue(name);
        return atom;
    }

    static Tree superInstance(Position position) {
        NameNode nameNode = new NameNode();
        nameNode.setName(Terms.superName(position));
        return nameNode;
    }

    static Tree thisInstance(Position position) {
        NameNode nameNode = new NameNode();
        nameNode.setName(Terms.thisName(position));
        return nameNode;
    }

    static Tree clone(Apply apply) {
        return Exprs.apply(apply.getPos(), apply.getChild(0), apply.getName(), apply.getChild(1));
    }
}
