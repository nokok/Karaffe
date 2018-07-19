package org.karaffe.compiler.frontend.karaffe.tasks;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.mir.Instruction;
import org.karaffe.compiler.base.mir.Instructions;
import org.karaffe.compiler.base.mir.block.BeginBlock;
import org.karaffe.compiler.base.mir.block.BeginClass;
import org.karaffe.compiler.base.mir.block.BeginMethod;
import org.karaffe.compiler.base.mir.block.EndBlock;
import org.karaffe.compiler.base.mir.block.EndClass;
import org.karaffe.compiler.base.mir.block.EndMethod;
import org.karaffe.compiler.base.mir.constant.Const;
import org.karaffe.compiler.base.mir.invoke.Invoke;
import org.karaffe.compiler.base.mir.io.Load;
import org.karaffe.compiler.base.mir.io.Store;
import org.karaffe.compiler.base.mir.jump.IfJumpFalse;
import org.karaffe.compiler.base.mir.jump.Jump;
import org.karaffe.compiler.base.mir.jump.JumpTarget;
import org.karaffe.compiler.base.mir.rule.TypeNameRewriteRule;
import org.karaffe.compiler.base.mir.util.InstructionList;
import org.karaffe.compiler.base.mir.util.Label;
import org.karaffe.compiler.base.mir.util.attr.Attribute;
import org.karaffe.compiler.base.mir.util.attr.MethodInvocationAttribute;
import org.karaffe.compiler.base.mir.util.attr.ModifierAttribute;
import org.karaffe.compiler.base.mir.util.attr.ParameterAttribute;
import org.karaffe.compiler.base.mir.variable.ValDef;
import org.karaffe.compiler.base.task.AbstractTask;
import org.karaffe.compiler.base.task.NoDescriptionTask;
import org.karaffe.compiler.base.task.TaskResult;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.TreeVisitorAdapter;
import org.karaffe.compiler.base.tree.def.AssignmentDef;
import org.karaffe.compiler.base.tree.def.ClassDef;
import org.karaffe.compiler.base.tree.def.LetDef;
import org.karaffe.compiler.base.tree.def.MethodDef;
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
import org.karaffe.compiler.base.tree.term.EmptyTree;
import org.karaffe.compiler.base.tree.term.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GenMIRTask extends AbstractTask implements NoDescriptionTask {

    @Override
    public String name() {
        return "frontend-karaffe-mir";
    }

    @Override
    public TaskResult run(CompilerContext context) {
        Instructions instructions = new InstructionList();
        Label rootLabel = Label.createRootLabel();
        instructions.add(new BeginBlock(rootLabel));
        Tree compilationUnit = context.getCompilationUnit();
        Instructions generated = compilationUnit.accept(new TreeVisitor(), null);
        instructions.addAll(generated);
        instructions.add(new EndBlock(rootLabel));
        context.setInstructions(instructions);
        return TaskResult.SUCCESSFUL;
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
        public Instructions visitSimpleImportDef(SimpleImport def, Label label) {
            Instructions instructions = new InstructionList();
            instructions.add(new TypeNameRewriteRule(def.getName().asSimpleName(), "L" + def.getName().asFullName().replaceAll("\\\\.", "/") + ";"));
            return instructions;
        }

        @Override
        public Instructions visitClassDef(ClassDef def, Label label) {
            LOGGER.trace("visitClassDef: {}", def);
            Instructions instructions = new InstructionList();
            Label classLabel = new Label(label, def.getName().toString());
            BeginClass beginClass = new BeginClass(classLabel);
            visitModifier(def.getModifiers()).forEach(beginClass::addAttribute);
            instructions.add(beginClass);
            def.acceptChildren(this, classLabel).stream().forEach(instructions::addAll);
            instructions.add(new EndClass(classLabel));
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
            String parameterTypes = def.getChild(0).getChildren().stream().map(Binding.class::cast).map(b -> b.getTypeName()).map(Path::asFullName).reduce((l, r) -> l + ", " + r).orElse("");
            Label methodLabel = new Label(label, def.getName() + "(" + parameterTypes + "):" + def.getTypeName());
            BeginMethod beginMethod = new BeginMethod(methodLabel);
            visitModifier(def.getModifiers()).forEach(beginMethod::addAttribute);
            beginMethod.setPosition(def.getName().getPos());
            instructions.add(beginMethod);
            List<ValDef> parameters = def.getChild(0)
                    .getChildren()
                    .stream()
                    .map(Binding.class::cast)
                    .map(b -> new ValDef(new Label(methodLabel, b.getName().toString()), b.getTypeName().toString()))
                    .collect(Collectors.toList());
            parameters.forEach(p -> p.addAttribute(new ParameterAttribute()));
            instructions.addAll(parameters);
            def.acceptChildren(1, this, methodLabel).stream().forEach(instructions::addAll);
            instructions.add(new EndMethod(methodLabel));
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
            instructions.add(new BeginBlock(blockLabel));
            block.acceptChildren(this, blockLabel).forEach(instructions::addAll);
            instructions.add(new EndBlock(blockLabel));
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

            Instruction instruction;
            switch (atom.getAtomKind()) {
            case INTEGER:
            case STRING:
                instruction = new Const(atom.getValue(), atom.getAtomKind().name());
                break;
            case IDENTIFIER:
                instruction = new Load(new Label(label, atom.getValue()));
                break;
            default:
                throw new IllegalStateException();
            }
            instruction.setPosition(atom.getPos());
            instructions.add(instruction);
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
            org.karaffe.compiler.base.mir.constant.Binding b = new org.karaffe.compiler.base.mir.constant.Binding(new Label(label, name.toString()), typeName.asFullName());
            instructions.add(b);
            return instructions;
        }

        @Override
        public Instructions visitCast(Cast cast, Label label) {
            Instructions instructions = new InstructionList();
            instructions.add(new org.karaffe.compiler.base.mir.Cast(cast.getTypeName().asFullName()));
            return instructions;
        }

        @Override
        public Instructions visitWhileExpr(WhileExpr whileExpr, Label label) {
            Instructions instructions = new InstructionList();
            long index = seq++;
            Label whileBlockLabel = new Label(label, "whileBlock" + index);
            instructions.add(new BeginBlock(whileBlockLabel));
            Label beginWhile = new Label(label, "beginWhile" + index);
            Label endWhile = new Label(label, "endWhile" + index);
            instructions.add(new JumpTarget(beginWhile));
            Tree condition = whileExpr.getChild(0);
            Tree body = whileExpr.getChild(1);
            condition.acceptChildren(this, label).forEach(instructions::addAll);
            instructions.add(new IfJumpFalse(endWhile));
            body.acceptChildren(this, label).forEach(instructions::addAll);
            instructions.add(new Jump(beginWhile));
            instructions.add(new JumpTarget(endWhile));
            instructions.add(new EndBlock(whileBlockLabel));
            return instructions;
        }

        @Override
        public Instructions visitIfExpr(IfExpr ifExpr, Label label) {
            Instructions instructions = new InstructionList();
            long index = seq++;
            Tree cond = ifExpr.getChild(0);
            Tree thenExpr = ifExpr.getChild(1);
            Tree elseExpr = ifExpr.getChild(2);
            Label thenBlock = new Label(label, "then" + index);
            Label elseBlock = new Label(label, "else" + index);
            Label endBlock = new Label(label, "end" + index);

            cond.acceptChildren(this, label).forEach(instructions::addAll);
            instructions.add(new IfJumpFalse(elseBlock));

            instructions.add(new BeginBlock(thenBlock));
            thenExpr.acceptChildren(this, label).forEach(instructions::addAll);
            instructions.add(new Jump(endBlock));
            instructions.add(new EndBlock(thenBlock));

            instructions.add(new BeginBlock(elseBlock));
            instructions.add(new JumpTarget(elseBlock));
            elseExpr.acceptChildren(this, label).forEach(instructions::addAll);
            instructions.add(new EndBlock(elseBlock));
            instructions.add(new JumpTarget(endBlock));

            return instructions;
        }

        @Override
        public Instructions visitAssignmentDef(AssignmentDef simpleDef, Label label) {
            Instructions instructions = new InstructionList();
            Label l = new Label(label, simpleDef.getName().asSimpleName());
            Store s = new Store(l);
            simpleDef.accept(this, l).forEach(instructions::add);
            simpleDef.acceptChildren(this, l).forEach(instructions::addAll);
            instructions.add(s);
            return instructions;
        }

        private List<ModifierAttribute> visitModifier(List<Tree> modifiers) {
            return modifiers
                    .stream()
                    .map(Modifier.class::cast)
                    .map(ModifierAttribute::new)
                    .collect(Collectors.toList());
        }

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
