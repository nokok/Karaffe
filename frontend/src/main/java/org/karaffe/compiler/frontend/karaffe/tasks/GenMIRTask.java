package org.karaffe.compiler.frontend.karaffe.tasks;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.task.NoDescriptionTask;
import org.karaffe.compiler.base.task.TaskResult;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.TreeVisitorAdapter;
import org.karaffe.compiler.base.tree.def.ClassDef;
import org.karaffe.compiler.base.tree.def.LetDef;
import org.karaffe.compiler.base.tree.def.MethodDef;
import org.karaffe.compiler.base.tree.expr.Apply;
import org.karaffe.compiler.base.tree.expr.Atom;
import org.karaffe.compiler.base.tree.expr.Binding;
import org.karaffe.compiler.base.tree.expr.Block;
import org.karaffe.compiler.base.tree.expr.Tuple;
import org.karaffe.compiler.base.tree.term.EmptyTree;
import org.karaffe.compiler.base.tree.term.Path;
import org.karaffe.compiler.mir.InstructionType;
import org.karaffe.compiler.mir.Instructions;
import org.karaffe.compiler.mir.block.Begin;
import org.karaffe.compiler.mir.block.End;
import org.karaffe.compiler.mir.constant.Const;
import org.karaffe.compiler.mir.invoke.Invoke;
import org.karaffe.compiler.mir.io.Store;
import org.karaffe.compiler.mir.util.InstructionList;
import org.karaffe.compiler.mir.util.Label;
import org.karaffe.compiler.mir.util.attr.Attribute;
import org.karaffe.compiler.mir.util.attr.MethodInvocationAttribute;
import org.karaffe.compiler.mir.util.attr.ParameterAttribute;
import org.karaffe.compiler.mir.variable.ValDef;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GenMIRTask extends AbstractTask implements NoDescriptionTask {

    private Instructions instructions;

    @Override
    public String name() {
        return "gen-mir";
    }

    @Override
    public TaskResult run(CompilerContext context) {
        this.instructions = new InstructionList();
        Label rootLabel = Label.createRootLabel();
        this.instructions.add(new Begin(InstructionType.PROGRAM, rootLabel));

        Tree compilationUnit = context.getCompilationUnit();
        Instructions generated = compilationUnit.accept(new TreeVisitor(), null);
        this.instructions.addAll(generated);

        this.instructions.add(new End(rootLabel));
        return TaskResult.SUCCESS;
    }

    private static class TreeVisitor extends TreeVisitorAdapter<Instructions, Label> {

        private static final Logger LOGGER = LoggerFactory.getLogger(TreeVisitor.class);
        private long seq = 0;

        @Override
        public Instructions visitCompileUnit(Tree.CompilationUnit tree, Label parent) {
            LOGGER.trace("visitCompileUnit: {}", tree);
            Instructions instructions = new InstructionList();
            Label rootLabel = Label.createRootLabel();
            List<Instructions> accepted = tree.acceptChildren(this, rootLabel);
            accepted.stream().filter(Objects::nonNull).forEach(instructions::addAll);
            return instructions;
        }

        @Override
        public Instructions visitClassDef(ClassDef def, Label label) {
            LOGGER.trace("visitClassDef: {}", def);
            Instructions instructions = new InstructionList();
            Label classLabel = new Label(label, def.getName().toString());
            instructions.add(new Begin(InstructionType.CLASS, classLabel));
            def.acceptChildren(this, classLabel).stream().forEach(instructions::addAll);
            instructions.add(new End(classLabel));
            return instructions;
        }

        @Override
        public Instructions visitTemplate(Tree.Template def, Label label) {
            LOGGER.trace("visitTemplate: {}", def);
            Instructions instructions = new InstructionList();
            def.acceptChildren(this, label).stream().forEach(instructions::addAll);
            return instructions;
        }

        @Override
        public Instructions visitMethodDef(MethodDef def, Label label) {
            LOGGER.trace("visitMethodDef: {}", def);
            Instructions instructions = new InstructionList();
            Label methodLabel = new Label(label, def.getName().toString() + def.getChild(0) + ":" + def.getTypeName());

            instructions.add(new Begin(InstructionType.METHOD, methodLabel));
            List<ValDef> parameters = def.getChild(0)
                    .getChildren()
                    .stream()
                    .map(Binding.class::cast)
                    .map(b -> new ValDef(new Label(methodLabel, b.getName().toString()), b.getTypeName().toString()))
                    .collect(Collectors.toList());
            parameters.forEach(p -> p.addAttribute(new ParameterAttribute()));
            instructions.addAll(parameters);
            def.acceptChildren(1, this, methodLabel).stream().forEach(instructions::addAll);
            instructions.add(new End(methodLabel));
            return instructions;
        }

        @Override
        public Instructions visitLetDef(LetDef simpleDef, Label parent) {
            LOGGER.trace("visitLetDef: {}", simpleDef);
            Instructions instructions = new InstructionList();
            Label valLabel = new Label(parent, simpleDef.getName().asSimpleName());
            ValDef valDef = new ValDef(valLabel, simpleDef.getTypeName().asFullName());
            valDef.setPosition(simpleDef.getPos());
            instructions.add(valDef);
            Instructions accept = simpleDef.getBody().get(0).accept(this, valLabel);
            instructions.addAll(accept);
            Store store = new Store(valLabel);
            store.setPosition(simpleDef.getName().getPos());
            instructions.add(store);
            return instructions;
        }

        @Override
        public Instructions visitBlock(Block block, Label label) {
            LOGGER.trace("visitBlock: {}", block);
            Instructions instructions = new InstructionList();
            Label blockLabel = new Label(label, String.valueOf(seq++));
            instructions.add(new Begin(InstructionType.BLOCK, blockLabel));
            block.acceptChildren(this, blockLabel).forEach(instructions::addAll);
            instructions.add(new End(blockLabel));
            return instructions;
        }

        @Override
        public Instructions visitApply(Apply apply, Label label) {
            LOGGER.trace("visitApply: {}", apply);
            Instructions instructions = new InstructionList();
            List<Instructions> acceptChildren = apply.acceptChildren(this, label);
            acceptChildren.forEach(instructions::addAll);
            Invoke invoke = new Invoke(apply.getName().toString());
            invoke.setPosition(apply.getPos());
            instructions.add(invoke);
            Attribute methodInvocation = new MethodInvocationAttribute(seq++);
            instructions.forEach(i -> i.addAttribute(methodInvocation));
            return instructions;
        }

        @Override
        public Instructions visitEmpty(EmptyTree emptyTree, Label label) {
            LOGGER.trace("visitEmpty: {}", emptyTree);
            return new InstructionList();
        }

        @Override
        public Instructions visitAtom(Atom atom, Label label) {
            LOGGER.trace("visitAtom: {}", atom);
            Instructions instructions = new InstructionList();
            Const aConst = new Const(atom.getValue(), atom.getAtomKind().name());
            aConst.setPosition(atom.getPos());
            instructions.add(aConst);
            return instructions;
        }

        @Override
        public Instructions visitTuple(Tuple tuple, Label label) {
            Instructions instructions = new InstructionList();
            tuple.acceptChildren(this, label).forEach(instructions::addAll);
            return instructions;
        }

        @Override
        public Instructions visitBinding(Binding binding, Label label) {
            Instructions instructions = new InstructionList();
            Path typeName = binding.getTypeName();
            Path name = binding.getName();
            org.karaffe.compiler.mir.constant.Binding b = new org.karaffe.compiler.mir.constant.Binding(new Label(label, name.toString()), typeName.asFullName());
            instructions.add(b);
            return instructions;
        }
    }

    public Instructions getInstructions() {
        if (this.instructions == null) {
            throw new IllegalStateException();
        }
        return this.instructions;
    }

    @Override
    public boolean isRunnable(CompilerContext context) {
        return context.getCompilationUnit() != null;
    }

    @Override
    public boolean changed() {
        return true;
    }
}
