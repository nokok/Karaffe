package org.karaffe.compiler.base.tree.expr;

import org.antlr.v4.runtime.tree.TerminalNode;
import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.term.Name;
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

    static Tree apply(Tree target, Name methodName, Tree arg) {
        Apply apply = new Apply();
        apply.addChild(target);
        apply.addChild(methodName);
        apply.addChild(arg);
        return apply;
    }

    static Tree apply(Tree target, Name methodName, List<Tree> args) {
        Apply apply = new Apply();
        apply.addChild(target);
        apply.addChild(methodName);

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

    static Tree ifExpr(Tree condition, Tree thenBlock, Tree elseBlock) {
        IfExpr ifExpr = new IfExpr();
        ifExpr.addChild(condition);
        ifExpr.addChild(thenBlock);
        ifExpr.addChild(elseBlock);
        return ifExpr;
    }

    static Tree whileExpr(Tree condition, Tree body) {
        WhileExpr whileExpr = new WhileExpr();
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

    static Tree unaryApply(Operator operator, Tree expr) {
        Apply apply = new Apply();
        apply.addChild(Terms.typeName("Unary"));
        apply.addChild(operator);
        apply.addChild(expr);
        return apply;
    }

    static Tree cast(Tree expr, Tree typeName) {
        Cast cast = new Cast();
        cast.addChild(expr);
        cast.addChild(typeName);
        return cast;
    }

    static Tree newInstance(Name typeName, Tree args) {
        Apply apply = new Apply();
        apply.addChild(typeName);
        apply.addChild(Terms.varName("<init>"));
        apply.addChild(args);
        return apply;
    }
}
