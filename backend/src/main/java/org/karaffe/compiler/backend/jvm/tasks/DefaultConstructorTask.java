package org.karaffe.compiler.backend.jvm.tasks;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.mir.Instruction;
import org.karaffe.compiler.base.mir.InstructionType;
import org.karaffe.compiler.base.mir.Instructions;
import org.karaffe.compiler.base.mir.block.BeginClass;
import org.karaffe.compiler.base.mir.block.BeginConstructor;
import org.karaffe.compiler.base.mir.block.EndConstructor;
import org.karaffe.compiler.base.mir.invoke.Invoke;
import org.karaffe.compiler.base.mir.io.Load;
import org.karaffe.compiler.base.mir.jump.Return;
import org.karaffe.compiler.base.mir.util.InstructionList;
import org.karaffe.compiler.base.mir.util.Label;
import org.karaffe.compiler.base.task.AbstractTask;
import org.karaffe.compiler.base.task.BackendTask;
import org.karaffe.compiler.base.task.TaskResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultConstructorTask extends AbstractTask implements BackendTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultConstructorTask.class);

    @Override
    public TaskResult run(Instructions instructions, CompilerContext context) {
        Instructions dest = new InstructionList();
        dest.addAll(instructions);
        boolean inClass = false;
        boolean hasCtor = false;
        int insertIndex = 0;
        Label parentClass = null;
        for (int index = 0; index < instructions.size(); index++) {
            Instruction instruction = instructions.get(index);
            if (instruction.getInstType() == InstructionType.BEGINCLASS) {
                inClass = true;
                insertIndex = index + 1;
                BeginClass beginClass = (BeginClass) instruction;
                parentClass = beginClass.getLabel();
                LOGGER.debug("Begin : {}", parentClass);
            }
            if (instruction.getInstType() == InstructionType.BEGINCONSTRUCTOR && inClass) {
                hasCtor = true;
                LOGGER.debug("Constructor found");
            }
            if (instruction.getInstType() == InstructionType.ENDCLASS) {
                inClass = false;
                if (!hasCtor) {
                    LOGGER.debug("Insert Default constructor");
                    Instructions ctor = new InstructionList();
                    Label ctorLabel = new Label(parentClass, "<init>():void");
                    BeginConstructor beginConstructor = new BeginConstructor(ctorLabel);
                    Load loadThis = new Load(new Label("this"));
                    Invoke invokeCtor = new Invoke("super#<init>");
                    Return returnVoid = new Return();
                    EndConstructor endConstructor = new EndConstructor(ctorLabel);
                    ctor.add(beginConstructor);
                    ctor.add(loadThis);
                    ctor.add(invokeCtor);
                    ctor.add(returnVoid);
                    ctor.add(endConstructor);
                    dest.addAll(insertIndex, ctor);
                }
            }
        }
        context.setInstructions(dest);
        return TaskResult.SUCCESSFUL;
    }

    @Override
    public String name() {
        return "backend-jvm-defaultctor";
    }

    @Override
    public String description() {
        return "Generate default constructor";
    }

    @Override
    public boolean changed() {
        return true;
    }
}
