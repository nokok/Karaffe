package org.karaffe.compiler.frontend.karaffe.tasks.postparse;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.task.ASTTask;
import org.karaffe.compiler.base.task.AbstractTask;
import org.karaffe.compiler.base.task.TaskResult;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.TreeVisitor;
import org.karaffe.compiler.base.tree.def.AssignmentDef;
import org.karaffe.compiler.base.tree.def.ClassDef;
import org.karaffe.compiler.base.tree.def.LetDef;
import org.karaffe.compiler.base.tree.def.MethodDef;
import org.karaffe.compiler.base.tree.def.OnDemandImport;
import org.karaffe.compiler.base.tree.def.PackageDef;
import org.karaffe.compiler.base.tree.def.SimpleImport;
import org.karaffe.compiler.base.tree.expr.Apply;
import org.karaffe.compiler.base.tree.expr.Atom;
import org.karaffe.compiler.base.tree.expr.Binding;
import org.karaffe.compiler.base.tree.expr.Block;
import org.karaffe.compiler.base.tree.expr.Cast;
import org.karaffe.compiler.base.tree.expr.IfExpr;
import org.karaffe.compiler.base.tree.expr.Tuple;
import org.karaffe.compiler.base.tree.expr.WhileExpr;
import org.karaffe.compiler.base.tree.modifier.Modifier;
import org.karaffe.compiler.base.tree.stmt.ReturnStatement;
import org.karaffe.compiler.base.tree.term.EmptyTree;
import org.karaffe.compiler.base.tree.term.NameNode;

public class ConfigureParentTreeTask extends AbstractTask implements ASTTask {
    @Override
    public TaskResult run(Tree compilationUnit, CompilerContext context) {
        TreeVisitor<Tree, Tree> visitor = new TreeVisitor<Tree, Tree>() {

            @Override
            public Tree visit(LetDef simpleDef, Tree tree) {
                simpleDef.acceptChildren(this, simpleDef);
                simpleDef.setParent(tree);
                return simpleDef;
            }

            @Override
            public Tree visit(AssignmentDef simpleDef, Tree tree) {
                simpleDef.acceptChildren(this, simpleDef);
                simpleDef.setParent(tree);
                return simpleDef;
            }

            @Override
            public Tree visit(ClassDef def, Tree tree) {
                def.acceptChildren(this, def);
                def.setParent(tree);
                return def;
            }

            @Override
            public Tree visit(SimpleImport def, Tree tree) {
                def.acceptChildren(this, def);
                def.setParent(tree);
                return def;
            }

            @Override
            public Tree visit(OnDemandImport onDemandImport, Tree tree) {
                onDemandImport.acceptChildren(this, onDemandImport);
                onDemandImport.setParent(tree);
                return onDemandImport;
            }

            @Override
            public Tree visit(MethodDef def, Tree tree) {
                def.acceptChildren(this, def);
                def.setParent(tree);
                return def;
            }

            @Override
            public Tree visit(PackageDef def, Tree tree) {
                def.acceptChildren(this, def);
                def.setParent(tree);
                return def;
            }

            @Override
            public Tree visitStaticMod(Modifier modifier, Tree tree) {
                modifier.acceptChildren(this, modifier);
                modifier.setParent(tree);
                return modifier;
            }

            @Override
            public Tree visitPublicMod(Modifier modifier, Tree tree) {
                modifier.acceptChildren(this, modifier);
                modifier.setParent(tree);
                return modifier;
            }

            @Override
            public Tree visitSyntheticMod(Modifier simpleModifier, Tree tree) {
                simpleModifier.acceptChildren(this, simpleModifier);
                simpleModifier.setParent(tree);
                return simpleModifier;
            }

            @Override
            public Tree visit(Tree.CompilationUnit tree, Tree tree2) {
                tree.acceptChildren(this, tree);
                tree.setParent(tree);
                return tree;
            }

            @Override
            public Tree visit(Tree.Template template, Tree tree) {
                template.acceptChildren(this, template);
                template.setParent(tree);
                return template;
            }

            @Override
            public Tree visit(Apply apply, Tree tree) {
                apply.acceptChildren(this, apply);
                apply.setParent(tree);
                return apply;
            }

            @Override
            public Tree visit(Atom atom, Tree tree) {
                atom.acceptChildren(this, atom);
                atom.setParent(tree);
                return atom;
            }

            @Override
            public Tree visit(Block block, Tree tree) {
                block.acceptChildren(this, block);
                block.setParent(tree);
                return block;
            }

            @Override
            public Tree visit(Tuple tuple, Tree tree) {
                tuple.acceptChildren(this, tuple);
                tuple.setParent(tree);
                return tuple;
            }

            @Override
            public Tree visit(IfExpr ifExpr, Tree tree) {
                ifExpr.acceptChildren(this, ifExpr);
                ifExpr.setParent(tree);
                return ifExpr;
            }

            @Override
            public Tree visit(WhileExpr whileExpr, Tree tree) {
                whileExpr.acceptChildren(this, whileExpr);
                whileExpr.setParent(tree);
                return whileExpr;
            }

            @Override
            public Tree visit(EmptyTree emptyTree, Tree tree) {
                emptyTree.acceptChildren(this, emptyTree);
                emptyTree.setParent(tree);
                return emptyTree;
            }

            @Override
            public Tree visit(Cast cast, Tree tree) {
                cast.acceptChildren(this, cast);
                cast.setParent(tree);
                return cast;
            }

            @Override
            public Tree visit(Binding binding, Tree tree) {
                binding.acceptChildren(this, binding);
                binding.setParent(tree);
                return binding;
            }

            @Override
            public Tree visit(ReturnStatement returnStatement, Tree tree) {
                returnStatement.acceptChildren(this, returnStatement);
                returnStatement.setParent(tree);
                return returnStatement;
            }

            @Override
            public Tree visit(NameNode nameNode, Tree tree) {
                nameNode.acceptChildren(this, nameNode);
                nameNode.setParent(tree);
                return nameNode;
            }
        };
        Tree accept = compilationUnit.accept(visitor);
        context.setCompilationUnit(accept);

        return TaskResult.SUCCESSFUL;
    }

    @Override
    public String name() {
        return "frontend-karaffe-configureparent";
    }

    @Override
    public String description() {
        return "Configure parent tree each children";
    }

    @Override
    public boolean changed() {
        return true;
    }
}
