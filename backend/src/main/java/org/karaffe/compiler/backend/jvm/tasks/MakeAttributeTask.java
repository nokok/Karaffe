package org.karaffe.compiler.backend.jvm.tasks;

import net.nokok.azm.Opcodes;
import org.karaffe.compiler.backend.jvm.attr.InvokeMethodAttribute;
import org.karaffe.compiler.backend.jvm.attr.NewClassAttribute;
import org.karaffe.compiler.backend.jvm.attr.NewMethodAttribute;
import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.mir.instructions.InstructionType;
import org.karaffe.compiler.base.mir.instructions.IR;
import org.karaffe.compiler.base.mir.instructions.block.BeginClass;
import org.karaffe.compiler.base.mir.instructions.block.BeginMethod;
import org.karaffe.compiler.base.mir.instructions.invoke.InvokeMethod;
import org.karaffe.compiler.base.mir.instructions.jump.Return;
import org.karaffe.compiler.base.task.AbstractTask;
import org.karaffe.compiler.base.task.MIRTask;
import org.karaffe.compiler.base.task.TaskResult;
import org.karaffe.compiler.base.util.Errors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class MakeAttributeTask extends AbstractTask implements MIRTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(MakeAttributeTask.class);

    @Override
    public String name() {
        return "transform-makeattr";
    }

    @Override
    public String description() {
        return "Make attributes";
    }

    @Override
    public boolean changed() {
        return true;
    }

    @Override
    public TaskResult run(IR instructions, CompilerContext context) {
        instructions.stream().filter(i -> i.getInstType() == InstructionType.BEGINCLASS).map(BeginClass.class::cast).forEach(beginClass -> {
            int access = Opcodes.ACC_PUBLIC;
            Class<?> superClass = Object.class;
            List<Class<?>> interfaces = new ArrayList<>();
            NewClassAttribute attribute = new NewClassAttribute(access, superClass, interfaces);
            beginClass.addAttribute(attribute);
        });
        instructions.stream().filter(i -> i.getInstType() == InstructionType.BEGINMETHOD).map(BeginMethod.class::cast).forEach(beginMethod -> {
            int access = Opcodes.ACC_PUBLIC;
            String name = beginMethod.getMethodName();
            Class<?> returnType;
            String returnTypeName = beginMethod.getReturnTypeName();
            try {
                if ("void".equals(returnTypeName)) {
                    returnType = void.class;
                } else if ("int".equals(returnTypeName)) {
                    returnType = int.class;
                } else if ("long".equals(returnTypeName)) {
                    returnType = long.class;
                } else if ("float".equals(returnTypeName)) {
                    returnType = float.class;
                } else if ("double".equals(returnTypeName)) {
                    returnType = double.class;
                } else if ("char".equals(returnTypeName)) {
                    returnType = char.class;
                } else if ("byte".equals(returnTypeName)) {
                    returnType = byte.class;
                } else if ("boolean".equals(returnTypeName)) {
                    returnType = boolean.class;
                } else {
                    returnType = Class.forName(returnTypeName);
                }
            } catch (ClassNotFoundException e) {
                LOGGER.error("Return Type not found : {}", beginMethod.getLabel(), e);
                Errors.symbolNotFound(beginMethod.getTree().getPos(), returnTypeName);
                returnType = void.class;
            }
            List<Class<?>> parameterTypes = new ArrayList<>();
            List<Class<?>> throwsTypes = new ArrayList<>();
            NewMethodAttribute attribute = new NewMethodAttribute(access, name, returnType, parameterTypes, throwsTypes);
            beginMethod.addAttribute(attribute);
        });
        instructions.stream().filter(i -> i.getInstType() == InstructionType.INVOKEMETHOD).map(InvokeMethod.class::cast).forEach(invoke -> {
            IR target = invoke.getTarget();
            String methodName = invoke.getMethodName();
            List<IR> parameterInstructions = invoke.getParameterInstructions();
            InvokeMethodAttribute attribute = new InvokeMethodAttribute(Object.class, methodName, new ArrayList<>());
            invoke.addAttribute(attribute);
        });

        instructions.stream().filter(i -> i.getInstType() == InstructionType.RETURN).map(Return.class::cast).forEach(r -> {
            LOGGER.info(r.getTree().getKind().toString());
        });

        return TaskResult.SUCCESSFUL;
    }
}
