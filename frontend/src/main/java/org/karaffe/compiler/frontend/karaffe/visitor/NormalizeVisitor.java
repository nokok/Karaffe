package org.karaffe.compiler.frontend.karaffe.visitor;

import org.karaffe.compiler.base.attr.NormalizedTree;
import org.karaffe.compiler.base.generator.Generator;
import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.base.tree.DefaultVisitor;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.TreeKind;
import org.karaffe.compiler.base.tree.def.Def;
import org.karaffe.compiler.base.tree.def.Defs;
import org.karaffe.compiler.base.tree.expr.Apply;
import org.karaffe.compiler.base.tree.expr.Atom;
import org.karaffe.compiler.base.tree.expr.Exprs;
import org.karaffe.compiler.base.tree.expr.Tuple;
import org.karaffe.compiler.base.tree.stmt.Stmts;
import org.karaffe.compiler.base.tree.term.NameNode;
import org.karaffe.compiler.base.tree.term.Path;
import org.karaffe.compiler.base.tree.term.Terms;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NormalizeVisitor extends DefaultVisitor<Generator<String>> {

    @Override
    public Tree visitApply(Apply tree, Generator<String> generator) {
        if (this.isNormalizable(tree)) {
            Position basePos = tree.getPos();
            List<Tree> children = new ArrayList<>();
            boolean isNormalizableTarget = this.isNormalizable(tree.getTarget());
            boolean isNormalizableArgs = this.isNormalizable(tree.getArgs());
            Tree target;
            if (isNormalizableTarget) {
                Tree acceptedTarget = tree.getTarget().accept(this, generator);
                Def t = Defs.letDef(basePos, Terms.varName(basePos, generator.generate()), acceptedTarget.getTypeName(), acceptedTarget);
                children.add(t);
                target = t.getName().toNameNode();
            } else {
                target = tree.getTarget();
            }
            Path methodName = tree.getName();
            Tree args;
            if (isNormalizableArgs) {
                Tree acceptedArgs = tree.getArgs().accept(this, generator);
                Def t = Defs.letDef(basePos, Terms.varName(basePos, generator.generate()), acceptedArgs.getTypeName(), acceptedArgs);
                children.add(t);
                args = t.getName().toNameNode();
            } else {
                args = tree.getArgs();
            }
            Tree apply = Exprs.apply(basePos, target, methodName, args);
            Def def = Defs.letDef(basePos, Terms.varName(basePos, generator.generate()), apply.getTypeName(), apply);
            children.add(def);
            children.add(Stmts.returnStmt(tree.getPos(), def.getName().toNameNode()));
            return Exprs.block(children);
        } else {
            return tree;
        }
    }

    @Override
    public Tree visitTuple(Tuple tree, Generator<String> generator) {
        if (tree.getChildren().isEmpty()) {
            return tree;
        }
        List<Tree> block = new ArrayList<>();
        for (Tree child : tree.getChildren()) {
            Tree accept = child.accept(this, generator);
            Def letDef = Defs.letDef(child.getPos(), Terms.varName(child.getPos(), generator.generate()), child.getTypeName(), accept);
            block.add(letDef);
        }
        if (block.size() > 1) {
            List<Tree> collect = block.stream().map(b -> Exprs.pathToTree(b.getPos(), b.getName())).collect(Collectors.toList());
            block.add(Stmts.returnStmt(Position.noPos(), Exprs.tuple(collect)));
        }
        return Exprs.block(block);
    }

    @Override
    public Tree visitAtom(Atom tree, Generator<String> generator) {
        return tree;
    }

    @Override
    public Tree visitNameNode(NameNode tree, Generator<String> generator) {
        return tree;
    }

    public boolean isNormalizable(Tree tree) {
        if (tree == null) {
            return false;
        }
        if (tree.hasAttribute(NormalizedTree.class)) {
            return false;
        }
        if (tree.getKind() == TreeKind.EMPTY) {
            return false;
        }
        if (tree.getKind() == TreeKind.BLOCK) {
            return false;
        }
        if (tree.getKind() == TreeKind.NAME) {
            return false;
        }
        return true;
    }
}
