package org.karaffe.compiler.frontend.karaffe.tasks;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.attr.Attribute;
import org.karaffe.compiler.base.mir.instructions.Import;
import org.karaffe.compiler.base.mir.instructions.DeprecatedInstruction;
import org.karaffe.compiler.base.mir.instructions.DeprecatedInstructions;
import org.karaffe.compiler.base.mir.instructions.attr.InvokingSetAttribute;
import org.karaffe.compiler.base.mir.instructions.attr.ModifierAttribute;
import org.karaffe.compiler.base.mir.instructions.attr.ParameterAttribute;
import org.karaffe.compiler.base.mir.instructions.block.BeginBlock;
import org.karaffe.compiler.base.mir.instructions.block.BeginClass;
import org.karaffe.compiler.base.mir.instructions.block.BeginMethod;
import org.karaffe.compiler.base.mir.instructions.block.EndBlock;
import org.karaffe.compiler.base.mir.instructions.block.EndClass;
import org.karaffe.compiler.base.mir.instructions.block.EndMethod;
import org.karaffe.compiler.base.mir.instructions.constant.ConstInt;
import org.karaffe.compiler.base.mir.instructions.constant.ConstString;
import org.karaffe.compiler.base.mir.instructions.instance.Load;
import org.karaffe.compiler.base.mir.instructions.instance.Store;
import org.karaffe.compiler.base.mir.instructions.invoke.InvokeMethod;
import org.karaffe.compiler.base.mir.instructions.jump.IfJumpFalse;
import org.karaffe.compiler.base.mir.instructions.jump.Jump;
import org.karaffe.compiler.base.mir.instructions.jump.JumpTarget;
import org.karaffe.compiler.base.mir.instructions.jump.Return;
import org.karaffe.compiler.base.mir.instructions.util.InstructionList;
import org.karaffe.compiler.base.mir.instructions.util.Label;
import org.karaffe.compiler.base.mir.instructions.variable.ValDef;
import org.karaffe.compiler.base.task.AbstractTask;
import org.karaffe.compiler.base.task.TaskResult;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.TreeKind;
import org.karaffe.compiler.base.tree.TreeVisitorAdapter;
import org.karaffe.compiler.base.tree.def.AssignmentDef;
import org.karaffe.compiler.base.tree.def.ClassDef;
import org.karaffe.compiler.base.tree.def.Def;
import org.karaffe.compiler.base.tree.def.DefKind;
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
import org.karaffe.compiler.base.tree.stmt.ReturnStatement;
import org.karaffe.compiler.base.tree.term.EmptyTree;
import org.karaffe.compiler.base.tree.term.NameNode;
import org.karaffe.compiler.base.tree.term.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GenMIRTask extends AbstractTask {

    @Override
    public String name() {
        return "frontend-karaffe-mir";
    }

    @Override
    public String description() {
        return "Generate MIR";
    }

    @Override
    public TaskResult run(CompilerContext context) {
        DeprecatedInstructions instructions = new InstructionList();
        Label rootLabel = Label.createRootLabel();
        BeginBlock beginBlock = new BeginBlock(rootLabel);
        Tree compilationUnit = context.getCompilationUnit();
        EndBlock endBlock = new EndBlock(rootLabel);
        DeprecatedInstructions generated = compilationUnit.accept(new TreeVisitor(), null);
        instructions.add(beginBlock);
        instructions.addAll(generated);
        instructions.add(endBlock);
        context.setInstructions(instructions);
        return TaskResult.SUCCESSFUL;
    }

    private static class TreeVisitor extends TreeVisitorAdapter<DeprecatedInstructions, Label> {

        private static final Logger LOGGER = LoggerFactory.getLogger(TreeVisitor.class);
        private long seq = 0;

        @Override
        public DeprecatedInstructions visitCompileUnit(Tree.CompilationUnit tree, Label parent) {
            LOGGER.trace("visitCompileUnit: {}", tree);
            DeprecatedInstructions instructions = new InstructionList();
            Label rootLabel = Label.createRootLabel();
            List<DeprecatedInstructions> accepted = tree.acceptChildren(this, rootLabel);
            accepted.stream().filter(Objects::nonNull).forEach(instructions::addAll);
            instructions.forEach(i -> i.setTree(tree));
            return instructions;
        }

        @Override
        public DeprecatedInstructions visitSimpleImportDef(SimpleImport def, Label label) {
            DeprecatedInstructions instructions = new InstructionList();
            instructions.add(new Import(def.getName()));
            instructions.forEach(i -> i.setTree(def));
            return instructions;
        }

        @Override
        public DeprecatedInstructions visitClassDef(ClassDef def, Label label) {
            LOGGER.trace("visitClassDef: {}", def);
            DeprecatedInstructions instructions = new InstructionList();
            Label classLabel = new Label(label, def.getName().toString());
            BeginClass beginClass = new BeginClass(classLabel);
            visitModifier(def.getModifiers()).forEach(beginClass::addAttribute);
            instructions.add(beginClass);
            def.acceptChildren(this, classLabel).stream().forEach(instructions::addAll);
            instructions.add(new EndClass(classLabel));
            instructions.forEach(i -> i.setTree(def));
            return instructions;
        }

        @Override
        public DeprecatedInstructions visitTemplate(Tree.Template def, Label label) {
            LOGGER.trace("visitTemplate: {}", def);
            DeprecatedInstructions instructions = new InstructionList();
            def.acceptChildren(this, label).stream().forEach(instructions::addAll);
            instructions.forEach(i -> i.setTree(def));
            return instructions;
        }

        @Override
        public DeprecatedInstructions visitMethodDef(MethodDef def, Label label) {
            LOGGER.trace("visitMethodDef: {}", def);
            DeprecatedInstructions instructions = new InstructionList();
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
            instructions.forEach(i -> i.setTree(def));
            return instructions;
        }

        @Override
        public DeprecatedInstructions visitLetDef(LetDef simpleDef, Label parent) {
            LOGGER.trace("visitLetDef: {}", simpleDef);
            DeprecatedInstructions instructions = new InstructionList();
            Label valLabel = new Label(parent, simpleDef.getName().asSimpleName());
            ValDef valDef = new ValDef(valLabel, simpleDef.getTypeName().asFullName());
            valDef.setPosition(simpleDef.getPos());
            instructions.add(valDef);
            DeprecatedInstructions accept = simpleDef.getBody().get(0).accept(this, valLabel);
            instructions.addAll(accept);
            Store store = new Store(valLabel);
            store.setPosition(simpleDef.getName().getPos());
            instructions.add(store);
            instructions.forEach(i -> i.setTree(simpleDef));
            return instructions;
        }

        @Override
        public DeprecatedInstructions visitBlock(Block block, Label label) {
            LOGGER.trace("visitBlock: {}", block);
            DeprecatedInstructions instructions = new InstructionList();
            Label blockLabel = new Label(label, String.valueOf(seq++));
            instructions.add(new BeginBlock(blockLabel));
            block.acceptChildren(this, blockLabel).forEach(instructions::addAll);
            instructions.add(new EndBlock(blockLabel));
            instructions.forEach(i -> i.setTree(block));
            return instructions;
        }

        @Override
        public DeprecatedInstructions visitApply(Apply apply, Label label) {
            LOGGER.trace("visitApply: {}", apply);
            DeprecatedInstructions instructions = new InstructionList();
            DeprecatedInstructions owner = apply.getChild(0).accept(this, label);
            String methodName = apply.getName().asFullName();
            List<DeprecatedInstructions> parameters = apply.acceptChildren(1, this, label);
            InvokeMethod invokeMethod = new InvokeMethod(owner, methodName, parameters);
            invokeMethod.setPosition(apply.getPos());
            instructions.add(invokeMethod);
            instructions.forEach(i -> i.setTree(apply));
            return instructions;
        }

        @Override
        public DeprecatedInstructions visitEmpty(EmptyTree emptyTree, Label label) {
            LOGGER.trace("visitEmpty: {}", emptyTree);
            return new InstructionList();
        }

        @Override
        public DeprecatedInstructions visitAtom(Atom atom, Label label) {
            LOGGER.trace("visitAtom: {}", atom);
            DeprecatedInstructions instructions = new InstructionList();

            DeprecatedInstruction instruction;
            switch (atom.getAtomKind()) {
            case INTEGER:
                instruction = new ConstInt(atom.getValue());
                break;
            case STRING:
                instruction = new ConstString(atom.getValue());
                break;
            case IDENTIFIER:
                instruction = new Load(new Label(label, atom.getValue()));
                break;
            default:
                throw new IllegalStateException();
            }
            instruction.setPosition(atom.getPos());
            instructions.add(instruction);
            instructions.forEach(i -> i.setTree(atom));
            return instructions;
        }

        @Override
        public DeprecatedInstructions visitTuple(Tuple tuple, Label label) {
            DeprecatedInstructions instructions = new InstructionList();
            tuple.acceptChildren(this, label).forEach(instructions::addAll);
            instructions.forEach(i -> i.setTree(tuple));
            return instructions;
        }

        @Override
        public DeprecatedInstructions visitBinding(Binding binding, Label label) {
            DeprecatedInstructions instructions = new InstructionList();
            Path typeName = binding.getTypeName();
            Path name = binding.getName();
            org.karaffe.compiler.base.mir.instructions.constant.Binding b = new org.karaffe.compiler.base.mir.instructions.constant.Binding(new Label(label, name.toString()), typeName.asFullName());
            instructions.add(b);
            instructions.forEach(i -> i.setTree(binding));
            return instructions;
        }

        @Override
        public DeprecatedInstructions visitCast(Cast cast, Label label) {
            DeprecatedInstructions instructions = new InstructionList();
            instructions.add(new org.karaffe.compiler.base.mir.instructions.Cast(cast.getTypeName().asFullName()));
            instructions.forEach(i -> i.setTree(cast));
            return instructions;
        }

        @Override
        public DeprecatedInstructions visitWhileExpr(WhileExpr whileExpr, Label label) {
            DeprecatedInstructions instructions = new InstructionList();
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
            instructions.forEach(i -> i.setTree(whileExpr));
            return instructions;
        }

        @Override
        public DeprecatedInstructions visitIfExpr(IfExpr ifExpr, Label label) {
            DeprecatedInstructions instructions = new InstructionList();
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

            instructions.forEach(i -> i.setTree(ifExpr));
            return instructions;
        }

        @Override
        public DeprecatedInstructions visitAssignmentDef(AssignmentDef simpleDef, Label label) {
            DeprecatedInstructions instructions = new InstructionList();
            Label l = new Label(label, simpleDef.getName().asSimpleName());
            Store s = new Store(l);
            simpleDef.accept(this, l).forEach(instructions::add);
            simpleDef.acceptChildren(this, l).forEach(instructions::addAll);
            instructions.add(s);
            instructions.forEach(i -> i.setTree(simpleDef));
            return instructions;
        }

        @Override
        public DeprecatedInstructions visitReturn(ReturnStatement returnStatement, Label label) {
            DeprecatedInstructions instructions = new InstructionList();
            returnStatement.acceptChildren(this, label).forEach(instructions::addAll);
            Return ret = new Return();
            Tree parent = returnStatement.getParent();
            if (parent.getKind() == TreeKind.DEF && ((Def) parent).getDefKind() == DefKind.METHOD) {
                MethodDef d = (MethodDef) parent;
                Path typeName = d.getTypeName();
                if (typeName.isVoidType()) {
                    
                }
            } else {
                throw new IllegalStateException("" + parent.getKind() + ", " + ((Def) parent).getDefKind());
            }
            if (instructions.size() > 0) {
                Attribute attribute = new InvokingSetAttribute(seq++);
                instructions.forEach(i -> i.addAttribute(attribute));
                ret.addAttribute(attribute);
            }
            instructions.add(ret);
            instructions.forEach(i -> i.setTree(returnStatement));
            return instructions;
        }

        @Override
        public DeprecatedInstructions visitNameNode(NameNode nameNode, Label label) {
            DeprecatedInstructions instructions = new InstructionList();
            Label loadName = new Label(label, nameNode.getName().asSimpleName());
            Load load = new Load(loadName);
            instructions.add(load);
            instructions.forEach(i -> i.setTree(nameNode));
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
