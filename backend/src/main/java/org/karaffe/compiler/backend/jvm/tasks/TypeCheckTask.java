package org.karaffe.compiler.backend.jvm.tasks;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.mir.Instructions;
import org.karaffe.compiler.base.mir.interpret.Interpreter;
import org.karaffe.compiler.base.mir.interpret.Scope;
import org.karaffe.compiler.base.mir.util.InstructionList;
import org.karaffe.compiler.base.task.AbstractTask;
import org.karaffe.compiler.base.task.BackendTask;
import org.karaffe.compiler.base.task.NoDescriptionTask;
import org.karaffe.compiler.base.task.TaskResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class TypeCheckTask extends AbstractTask implements BackendTask, NoDescriptionTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(TypeCheckTask.class);

    @Override
    public String name() {
        return "jvm-backend-typechecker";
    }

    @Override
    public boolean changed() {
        return false;
    }

    @Override
    public TaskResult run(Instructions instructions, CompilerContext context) {
        Interpreter interpreter = new Interpreter(instructions);
        Scope scope = new Scope();
        Stack<Instructions> nameStack = new Stack<>();
        interpreter.onScopeOpened(scopeName -> {
            LOGGER.debug("Push scope : {}", scopeName);
            scope.newScope();
            nameStack.push(new InstructionList());
        });
        interpreter.onNewName(name -> {
            scope.newName(name);
        });
        interpreter.onUseName(name -> {

        });
        interpreter.onScopeClosed(scopeName -> {
            LOGGER.debug("Pop scope : {}", scopeName);
            nameStack.pop();
            scope.discardCurrentScope();
        });
        interpreter.run();
        return TaskResult.SUCCESS;
    }
}
