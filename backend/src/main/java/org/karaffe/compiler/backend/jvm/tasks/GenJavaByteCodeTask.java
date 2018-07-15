package org.karaffe.compiler.backend.jvm.tasks;

import net.nokok.azm.ClassWriter;
import net.nokok.azm.Label;
import net.nokok.azm.MethodVisitor;
import net.nokok.azm.Opcodes;
import net.nokok.azm.Type;
import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.mir.Instruction;
import org.karaffe.compiler.base.mir.InstructionType;
import org.karaffe.compiler.base.mir.Instructions;
import org.karaffe.compiler.base.mir.block.BeginClass;
import org.karaffe.compiler.base.mir.block.BeginMethod;
import org.karaffe.compiler.base.mir.util.attr.ModifierAttribute;
import org.karaffe.compiler.base.mir.variable.ValDef;
import org.karaffe.compiler.base.task.AbstractTask;
import org.karaffe.compiler.base.task.BackendTask;
import org.karaffe.compiler.base.task.TaskResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;

public class GenJavaByteCodeTask extends AbstractTask implements BackendTask {

    private ClassWriter classWriter;
    private static final Logger LOGGER = LoggerFactory.getLogger(GenJavaByteCodeTask.class);

    @Override
    public TaskResult run(Instructions instructions, CompilerContext context) {
        List<String> classNames = new ArrayList<>();
        List<String> methodNames = new ArrayList<>();
        List<String> blockNames = new ArrayList<>();
        Stack<Queue<String>> localVarNames = new Stack<>();
        localVarNames.push(new ArrayDeque<>());

        String currentClassName = "";
        MethodVisitor methodVisitor = null;
        for (Instruction instruction : instructions) {
            LOGGER.debug("Instruction : {}", instruction);
            if (instruction.getInstType() == InstructionType.BEGINCLASS) {
                BeginClass b = (BeginClass) instruction;
                currentClassName = b.getClassName();
                classNames.add(currentClassName);
                classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
                classWriter.visit(
                        Opcodes.V1_8,
                        toJavaModifier(b),
                        b.getClassName(),
                        null,
                        Type.getInternalName(Object.class),
                        null
                );
            }
            if (instruction.getInstType() == InstructionType.BEGINMETHOD) {
                localVarNames.push(new ArrayDeque<>());
                BeginMethod beginMethod = (BeginMethod) instruction;
                methodNames.add(beginMethod.getMethodName());
                methodVisitor = classWriter.visitMethod(
                        toJavaModifier(beginMethod),
                        beginMethod.getMethodName(),
                        "(" + beginMethod.getParameters() + ")" + beginMethod.getReturnTypeName(),
                        null,
                        null
                );
            }
            if (instruction.getInstType() == InstructionType.VALDEF) {
                ValDef valDef = (ValDef) instruction;
                localVarNames.peek().add(valDef.getValName().getSimpleName());
                if (valDef.isParameter()) {
                    methodVisitor.visitParameter(valDef.getValName().getSimpleName(), toJavaModifier(valDef));
                } else {
                    methodVisitor.visitLocalVariable(valDef.getValName().getSimpleName(), valDef.getTypeName(), "", new Label(), new Label(), localVarNames.peek().size() - 1);
                }
            }
            if (instruction.getInstType() == InstructionType.ENDCLASS) {
                classWriter.visitEnd();
                byte[] bytes = classWriter.toByteArray();
                context.addBytecode(new File(currentClassName + ".class").toPath(), bytes);
            }
            if (instruction.getInstType() == InstructionType.ENDMETHOD) {
                localVarNames.pop();
                methodVisitor.visitEnd();
            }
        }
        return TaskResult.SUCCESS;
    }

    @Override
    public String name() {
        return "jvm-backend-bytecode";
    }

    @Override
    public String description() {
        return "Generate Java VM bytecode";
    }

    @Override
    public boolean changed() {
        return false;
    }

    private int toJavaModifier(Instruction instruction) {
        return toJavaModifier(instruction.getAttributes().stream().filter(i -> i instanceof ModifierAttribute).map(ModifierAttribute.class::cast).collect(Collectors.toList()));
    }

    private int toJavaModifier(List<ModifierAttribute> mods) {
        return mods.stream().map(m -> {
            switch (m.getModifier().getType()) {
            case PUBLIC:
                return Opcodes.ACC_PUBLIC;
            case STATIC:
                return Opcodes.ACC_STATIC;
            case SYNTHETIC:
                return Opcodes.ACC_SYNTHETIC;
            default:
                return 0;
            }
        }).reduce((l, r) -> l + r)
                .orElse(Opcodes.ACC_PUBLIC);
    }
}
