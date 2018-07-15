package org.karaffe.compiler.backend.jvm.tasks;

import net.nokok.azm.ClassWriter;
import net.nokok.azm.Opcodes;
import net.nokok.azm.Type;
import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.mir.Instruction;
import org.karaffe.compiler.base.mir.InstructionType;
import org.karaffe.compiler.base.mir.Instructions;
import org.karaffe.compiler.base.mir.block.Begin;
import org.karaffe.compiler.base.mir.block.BlockType;
import org.karaffe.compiler.base.mir.block.End;
import org.karaffe.compiler.base.mir.util.attr.BlockTypeAttribute;
import org.karaffe.compiler.base.task.AbstractTask;
import org.karaffe.compiler.base.task.BackendTask;
import org.karaffe.compiler.base.task.TaskResult;

public class GenJavaByteCodeTask extends AbstractTask implements BackendTask {

    @Override
    public TaskResult run(Instructions instructions, CompilerContext context) {
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        for (Instruction instruction : instructions) {
            if (instruction.getInstType() == InstructionType.BEGIN) {
                Begin begin = (Begin) instruction;
                BlockType blockType = begin.getAttributes()
                        .stream()
                        .filter(a -> a instanceof BlockTypeAttribute)
                        .map(BlockTypeAttribute.class::cast)
                        .map(BlockTypeAttribute::getBlockType)
                        .findFirst().orElseThrow(IllegalStateException::new);
                if (blockType == BlockType.CLASS) {
                    classWriter.visit(
                            Opcodes.V1_8,
                            Opcodes.ACC_PUBLIC + Opcodes.ACC_SUPER,
                            begin.getLabel().getSimpleName(),
                            null,
                            Type.getInternalName(Object.class),
                            null);
                }
            } else if (instruction.getInstType() == InstructionType.END) {
                End end = (End) instruction;
                classWriter.visitEnd();
            }
        }
        return TaskResult.SUCCESS;
    }

    @Override
    public String name() {
        return "gen-java-code";
    }

    @Override
    public String description() {
        return "Generate Java VM bytecode";
    }

    @Override
    public boolean changed() {
        return false;
    }
}
