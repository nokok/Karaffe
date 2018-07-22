package org.karaffe.compiler.backend.jvm.tasks;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.mir.Instruction;
import org.karaffe.compiler.base.mir.InstructionType;
import org.karaffe.compiler.base.mir.Instructions;
import org.karaffe.compiler.base.mir.invoke.InvokeSpecial;
import org.karaffe.compiler.base.mir.rule.TypeNameRewriteRule;
import org.karaffe.compiler.base.task.AbstractTask;
import org.karaffe.compiler.base.task.BackendTask;
import org.karaffe.compiler.base.task.TaskResult;

import java.util.ArrayList;
import java.util.List;

public class RenameTypeTask extends AbstractTask implements BackendTask {
    @Override
    public TaskResult run(Instructions instructions, CompilerContext context) {
        List<TypeNameRewriteRule> rewriteRules = new ArrayList<>();

        for (Instruction instruction : instructions) {
            if (instruction.getInstType() == InstructionType.TYPENAMEREWRITE) {
                rewriteRules.add((TypeNameRewriteRule) instruction);
            }
            if (instruction.getInstType() == InstructionType.INVOKESPECIAL) {
                // Ownerの置き換え
                InvokeSpecial invokeSpecial = (InvokeSpecial) instruction;
                invokeSpecial.getOwner();
                invokeSpecial.getReturnType();
                invokeSpecial.getParameters();
            }
            if (instruction.getInstType() == InstructionType.BEGINMETHOD) {
                //パラメータのおきかえ
            }
            if (instruction.getInstType() == InstructionType.VALDEF) {
                //型名の置き換え
            }
        }
        return TaskResult.SUCCESSFUL;
    }

    @Override
    public String name() {
        return "backend-jvm-renametype";
    }

    @Override
    public String description() {
        return "Rename from fully qualified class name to Java VM internal name";
    }

    @Override
    public boolean changed() {
        return true;
    }
}
