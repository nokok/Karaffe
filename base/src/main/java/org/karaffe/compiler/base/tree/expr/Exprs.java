package org.karaffe.compiler.base.tree.expr;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.base.tree.Tree;
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
        return apply;
    }

    static Tree apply(Position position, Tree target, Path methodName, List<Tree> args) {
        Apply apply = new Apply();
        apply.setPos(position);
        apply.setName(methodName);
        apply.addChild(target);
        for (Tree arg : args) {
            apply.addChild(arg);
        }
        return apply;
    }

    static Tree tuple(List<Tree> trees) {
        Tuple tuple = new Tuple();
        for (Tree tree : trees) {
            tuple.addChild(tree);
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
        ifExpr.addChild(condition);
        ifExpr.addChild(thenBlock);
        ifExpr.addChild(elseBlock);
        return ifExpr;
    }

    static Tree whileExpr(Position position, Tree condition, Tree body) {
        WhileExpr whileExpr = new WhileExpr();
        whileExpr.setPos(position);
        whileExpr.addChild(condition);
        whileExpr.addChild(body);
        return whileExpr;
    }

    static Tree block(List<Tree> trees) {
        Block block = new Block();
        for (Tree tree : trees) {
            block.addChild(tree);
        }
        return block;
    }

    static Tree unaryApply(Position position, Operator operator, Tree expr) {
        Apply apply = new Apply();
        apply.setPos(position);
        apply.setName(Terms.varName(operator.getPos(), operator.asFullName()));
        apply.addChild(expr);
        return apply;
    }

    static Tree cast(Position position, Tree tree, Path typeName) {
        Cast cast = new Cast();
        cast.setPos(position);
        cast.addChild(tree);
        cast.setTypeName(typeName);
        return cast;
    }

    static Tree newInstance(Position position, Path typeName, Tree args) {
        Apply apply = new Apply();
        apply.setPos(position);
        apply.setName(Terms.varName(Position.noPos(), "<init>"));
        apply.setTypeName(typeName);
        apply.addChild(args);
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
}
