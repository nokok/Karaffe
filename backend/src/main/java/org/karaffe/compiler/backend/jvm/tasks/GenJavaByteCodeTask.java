package org.karaffe.compiler.backend.jvm.tasks;

import net.nokok.azm.ClassWriter;
import net.nokok.azm.MethodVisitor;
import net.nokok.azm.Opcodes;
import net.nokok.azm.Type;
import org.karaffe.compiler.backend.jvm.attr.ClassAttribute;
import org.karaffe.compiler.backend.jvm.attr.ConstructorAttribute;
import org.karaffe.compiler.backend.jvm.attr.JavaVMScopeAttribute;
import org.karaffe.compiler.backend.jvm.attr.MethodAttribute;
import org.karaffe.compiler.backend.jvm.attr.ReturnOpcodesAttribute;
import org.karaffe.compiler.backend.jvm.attr.TypedInstructionAttribute;
import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.mir.Instruction;
import org.karaffe.compiler.base.mir.InstructionType;
import org.karaffe.compiler.base.mir.Instructions;
import org.karaffe.compiler.base.mir.block.BeginClass;
import org.karaffe.compiler.base.mir.block.BeginConstructor;
import org.karaffe.compiler.base.mir.block.BeginMethod;
import org.karaffe.compiler.base.mir.invoke.InvokeSpecial;
import org.karaffe.compiler.base.mir.io.Load;
import org.karaffe.compiler.base.mir.jump.Return;
import org.karaffe.compiler.base.mir.util.attr.ModifierAttribute;
import org.karaffe.compiler.base.mir.util.attr.ParameterAttribute;
import org.karaffe.compiler.base.mir.variable.ValDef;
import org.karaffe.compiler.base.task.AbstractTask;
import org.karaffe.compiler.base.task.BackendTask;
import org.karaffe.compiler.base.task.TaskResult;
import org.karaffe.compiler.base.util.Errors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GenJavaByteCodeTask extends AbstractTask implements BackendTask {

    private ClassWriter classWriter;
    private static final Logger LOGGER = LoggerFactory.getLogger(GenJavaByteCodeTask.class);

    @Override
    public TaskResult run(Instructions instructions, CompilerContext context) {
        List<String> classNames = new ArrayList<>();
        List<String> methodNames = new ArrayList<>();
        List<String> blockNames = new ArrayList<>();
        Stack<List<String>> localVarNames = new Stack<>();
        localVarNames.push(new ArrayList<>());

        String currentClassName = "";
        MethodVisitor methodVisitor = null;
        for (Instruction instruction : instructions) {
            LOGGER.debug("Instruction : {}", instruction);
            if (instruction.getInstType() == InstructionType.BEGINCLASS) {
                BeginClass b = (BeginClass) instruction;
                ClassAttribute classAttribute = b.getAttribute(ClassAttribute.class).orElseThrow(IllegalStateException::new);
                Class<?> classObject = classAttribute.getClassObject();
                classNames.add(currentClassName);
                classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
                classWriter.visit(
                        Opcodes.V1_8,
                        classObject.getModifiers(),
                        b.getClassName(),
                        null,
                        Type.getInternalName(classObject.getSuperclass()),
                        (String[]) Stream.of(classObject.getInterfaces()).map(Type::getInternalName).toArray()
                );
            }
            if (instruction.getInstType() == InstructionType.BEGINMETHOD) {
                localVarNames.push(new ArrayList<>());
                BeginMethod beginMethod = (BeginMethod) instruction;
                MethodAttribute methodAttribute = beginMethod.getAttribute(MethodAttribute.class).orElseThrow(IllegalStateException::new);
                Method method = methodAttribute.getMethodObject();
                methodNames.add(beginMethod.getMethodName());
                methodVisitor = classWriter.visitMethod(
                        method.getModifiers(),
                        method.getName(),
                        Type.getMethodDescriptor(method),
                        null,
                        (String[]) Stream.of(method.getExceptionTypes()).map(Type::getInternalName).toArray()
                );
            }
            if (instruction.getInstType() == InstructionType.BEGINCONSTRUCTOR) {
                localVarNames.push(new ArrayList<>());
                BeginConstructor beginConstructor = (BeginConstructor) instruction;
                methodNames.add(beginConstructor.getLabel().getSimpleName());
                ConstructorAttribute constructorAttribute = beginConstructor.getAttribute(ConstructorAttribute.class).orElseThrow(IllegalStateException::new);
                Constructor<?> constructor = constructorAttribute.getConstructor();
                String descriptor = Type.getConstructorDescriptor(constructor);
                methodVisitor = classWriter.visitMethod(
                        constructor.getModifiers(),
                        "<init>",
                        descriptor,
                        null,
                        (String[]) Stream.of(constructor.getExceptionTypes()).map(Type::getInternalName).toArray()
                );
            }
            if (instruction.getInstType() == InstructionType.LOAD) {
                Load load = (Load) instruction;
                if (load.getLoadName().getSimpleName().equals("this")) {
                    methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
                } else {
                    int index = localVarNames.peek().indexOf(load.getLoadName().getSimpleName());
                    if (index == -1) {
                        context.addReport(Errors.symbolNotFound(load.getPosition(), load.getLoadName().getSimpleName()));
                        return TaskResult.FAILED;
                    }
                    methodVisitor.visitVarInsn(Opcodes.ALOAD, index);
                }
            }
            if (instruction.getInstType() == InstructionType.INVOKESPECIAL) {
                InvokeSpecial invokeSpecial = (InvokeSpecial) instruction;
                methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, invokeSpecial.getOwner(), invokeSpecial.getMethodName(), "(" + invokeSpecial.getParameters() + ")" + invokeSpecial.getReturnType(), false);
            }
            if (instruction.getInstType() == InstructionType.RETURN) {
                Return returnInstruction = (Return) instruction;
                ReturnOpcodesAttribute returnOpcodesAttribute = returnInstruction.getAttribute(ReturnOpcodesAttribute.class).orElseThrow(IllegalStateException::new);
                methodVisitor.visitInsn(returnOpcodesAttribute.getOpcodes());
            }
            if (instruction.getInstType() == InstructionType.VALDEF) {
                ValDef valDef = (ValDef) instruction;
                localVarNames.peek().add(valDef.getValName().getSimpleName());

                TypedInstructionAttribute typedInstructionAttribute = valDef.getAttribute(TypedInstructionAttribute.class).orElseThrow(IllegalStateException::new);
                JavaVMScopeAttribute javaVMScopeAttribute = valDef.getAttribute(JavaVMScopeAttribute.class).orElseThrow(IllegalStateException::new);

                Class<?> valDefType = typedInstructionAttribute.getTypedInfo();

                if (valDef.hasAttribute(ParameterAttribute.class)) {
                    methodVisitor.visitParameter(valDef.getValName().getSimpleName(), toJavaModifier(valDef));
                } else {
                    methodVisitor.visitLocalVariable(
                            valDef.getValName().getSimpleName(),
                            Type.getInternalName(valDefType),
                            null,
                            javaVMScopeAttribute.getBegin(),
                            javaVMScopeAttribute.getEnd(),
                            localVarNames.peek().size() - 1);
                }
            }
            if (instruction.getInstType() == InstructionType.ENDCLASS) {
                classWriter.visitEnd();
                byte[] bytes = classWriter.toByteArray();
                context.addBytecode(new File(currentClassName + ".class").toPath(), bytes);
            }
            if (instruction.getInstType() == InstructionType.ENDMETHOD || instruction.getInstType() == InstructionType.ENDCONSTRUCTOR) {
                localVarNames.pop();
                methodVisitor.visitMaxs(0, 0);
                methodVisitor.visitEnd();
            }
        }
        return TaskResult.SUCCESSFUL;
    }

    @Override
    public String name() {
        return "backend-jvm-bytecode";
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
