package org.karaffe.compiler.backend.jvm.tasks.visitors;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.task.AbstractTask;
import org.karaffe.compiler.base.task.ReadOnlyTask;
import org.karaffe.compiler.base.task.TaskResult;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class WriteJavaBytecodeTask extends AbstractTask implements ReadOnlyTask {
    @Override
    public String name() {
        return "backend-jvm-writebytecode";
    }

    @Override
    public String description() {
        return "Write .class file(s)";
    }

    @Override
    public TaskResult run(CompilerContext context) {
        Map<Path, byte[]> bytecodes = context.getBytecodes();
        bytecodes.forEach((path, bytes) -> {
            try {
                Files.write(path, bytes);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        });
        return TaskResult.SUCCESSFUL;
    }

    @Override
    public boolean isRunnable(CompilerContext context) {
        return context.getBytecodes() != null && !context.getBytecodes().isEmpty();
    }
}
