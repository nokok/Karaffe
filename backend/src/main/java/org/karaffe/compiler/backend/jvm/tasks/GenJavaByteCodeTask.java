package org.karaffe.compiler.backend.jvm.tasks;

import net.nokok.azm.ClassWriter;
import net.nokok.azm.Opcodes;
import net.nokok.azm.Type;
import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.mir.Instructions;
import org.karaffe.compiler.base.mir.interpret.Interpreter;
import org.karaffe.compiler.base.task.AbstractTask;
import org.karaffe.compiler.base.task.BackendTask;
import org.karaffe.compiler.base.task.TaskResult;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.util.Stack;

public class GenJavaByteCodeTask extends AbstractTask implements BackendTask {

    @Override
    public TaskResult run(Instructions instructions, CompilerContext context) {
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        Interpreter interpreter = new Interpreter(instructions);
        Stack<String> classNameStack = new Stack<>();
        interpreter.onOpenClassDef(c -> {
            classNameStack.push(c.getSimpleName());
            classWriter.visit(
                    Opcodes.V1_8,
                    Opcodes.ACC_PUBLIC + Opcodes.ACC_SUPER,
                    c.getSimpleName(),
                    null,
                    Type.getInternalName(Object.class),
                    null);
        });

        interpreter.onCloseClassDef(c -> {
            classWriter.visitEnd();
            byte[] bytes = classWriter.toByteArray();
            File file = new File(classNameStack.pop() + ".class");
            try {
                Files.write(file.toPath(), bytes);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        });

        interpreter.run();
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
}
