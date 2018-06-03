package org.karaffe.compiler.base.tree.expr;

import org.antlr.v4.runtime.tree.TerminalNode;
import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.term.Name;

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
}
