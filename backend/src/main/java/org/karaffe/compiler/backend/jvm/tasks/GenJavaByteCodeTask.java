package org.karaffe.compiler.backend.jvm.tasks;

import net.nokok.azm.ClassVisitor;
import net.nokok.azm.ClassWriter;
import net.nokok.azm.FieldVisitor;
import net.nokok.azm.Label;
import net.nokok.azm.MethodVisitor;
import net.nokok.azm.Opcodes;
import net.nokok.azm.Type;
import net.nokok.azm.signature.SignatureReader;
import net.nokok.azm.signature.SignatureWriter;
import org.karaffe.compiler.backend.jvm.attr.ClassAttribute;
import org.karaffe.compiler.backend.jvm.attr.ConstructorAttribute;
import org.karaffe.compiler.backend.jvm.attr.FieldAttribute;
import org.karaffe.compiler.backend.jvm.attr.JavaVMScopeAttribute;
import org.karaffe.compiler.backend.jvm.attr.MethodAttribute;
import org.karaffe.compiler.backend.jvm.attr.ReturnOpcodesAttribute;
import org.karaffe.compiler.backend.jvm.attr.TypedInstructionAttribute;
import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.mir.Cast;
import org.karaffe.compiler.base.mir.Instruction;
import org.karaffe.compiler.base.mir.Instructions;
import org.karaffe.compiler.base.mir.block.BeginBlock;
import org.karaffe.compiler.base.mir.block.BeginClass;
import org.karaffe.compiler.base.mir.block.BeginConstructor;
import org.karaffe.compiler.base.mir.block.BeginMethod;
import org.karaffe.compiler.base.mir.block.EndBlock;
import org.karaffe.compiler.base.mir.constant.ConstInt;
import org.karaffe.compiler.base.mir.constant.ConstString;
import org.karaffe.compiler.base.mir.invoke.Invoke;
import org.karaffe.compiler.base.mir.io.Load;
import org.karaffe.compiler.base.mir.io.Store;
import org.karaffe.compiler.base.mir.jump.IfJumpFalse;
import org.karaffe.compiler.base.mir.jump.Jump;
import org.karaffe.compiler.base.mir.jump.JumpTarget;
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
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        Map<org.karaffe.compiler.base.mir.util.Label, Label> labelMap = new HashMap<>();
        Stack<List<String>> localVarNames = new Stack<>();
        localVarNames.push(new ArrayList<>());
        int currentJavaAPI = Opcodes.V1_8;

        String currentClassName = "";
        ClassVisitor classVisitor = null;
        MethodVisitor methodVisitor = null;
        FieldVisitor fieldVisitor = null;
        for (Instruction instruction : instructions) {
            LOGGER.debug("Instruction : {}", instruction);
            switch (instruction.getInstType()) {
            case BEGINCLASS: {
                BeginClass b = (BeginClass) instruction;
                ClassAttribute classAttribute = b.getAttribute(ClassAttribute.class).orElseThrow(IllegalStateException::new);
                Class<?> classObject = classAttribute.getClassObject();
                classNames.add(currentClassName);
                classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
                classWriter.visit(
                        currentJavaAPI,
                        classObject.getModifiers(),
                        b.getClassName(),
                        null,
                        Type.getInternalName(classObject.getSuperclass()),
                        (String[]) Stream.of(classObject.getInterfaces()).map(Type::getInternalName).toArray()
                );
            }
            break;
            case BEGINMETHOD: {
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
            break;
            case BEGINCONSTRUCTOR: {
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
            break;
            case LOAD: {
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
            break;
            case INVOKE: {
                Invoke invoke = (Invoke) instruction;
                int opcode;
                String owner;
                String name;
                String descriptor;
                boolean isInterface;
                if (invoke.hasAttribute(ConstructorAttribute.class)) {
                    ConstructorAttribute constructorAttribute = invoke.getAttribute(ConstructorAttribute.class).orElseThrow(IllegalStateException::new);
                    Constructor<?> constructor = constructorAttribute.getConstructor();
                    opcode = Opcodes.INVOKESPECIAL;
                    owner = Type.getInternalName(constructor.getDeclaringClass());
                    name = "<init>";
                    descriptor = Type.getConstructorDescriptor(constructor);
                    isInterface = constructor.getDeclaringClass().isInterface();
                } else if (invoke.hasAttribute(MethodAttribute.class)) {
                    MethodAttribute methodAttribute = invoke.getAttribute(MethodAttribute.class).orElseThrow(IllegalStateException::new);
                    Method methodObject = methodAttribute.getMethodObject();
                    if (methodObject.isAccessible()) {
                        opcode = Opcodes.INVOKEVIRTUAL;
                    } else {
                        //private method
                        opcode = Opcodes.INVOKESPECIAL;
                    }
                    owner = Type.getInternalName(methodObject.getDeclaringClass());
                    name = methodObject.getName();
                    descriptor = Type.getMethodDescriptor(methodObject);
                    isInterface = methodObject.getDeclaringClass().isInterface();
                } else {
                    throw new IllegalStateException();
                }
                methodVisitor.visitMethodInsn(
                        opcode,
                        owner,
                        name,
                        descriptor,
                        isInterface
                );
            }
            break;
            case RETURN: {
                Return returnInstruction = (Return) instruction;
                ReturnOpcodesAttribute returnOpcodesAttribute = returnInstruction.getAttribute(ReturnOpcodesAttribute.class).orElseThrow(IllegalStateException::new);
                methodVisitor.visitInsn(returnOpcodesAttribute.getOpcodes());
            }
            break;
            case VALDEF: {
                ValDef valDef = (ValDef) instruction;
                localVarNames.peek().add(valDef.getValName().getSimpleName());

                if (valDef.hasAttribute(FieldAttribute.class)) {
                    // field
                    FieldAttribute fieldAttribute = valDef.getAttribute(FieldAttribute.class).orElseThrow(IllegalStateException::new);
                    Field fieldObject = fieldAttribute.getFieldObject();
                    java.lang.reflect.Type genericType = fieldObject.getGenericType();

                    SignatureReader reader = new SignatureReader(genericType.getTypeName());
                    SignatureWriter visitor = new SignatureWriter();
                    reader.accept(visitor);

                    fieldVisitor = classVisitor.visitField(
                            fieldObject.getModifiers(),
                            fieldObject.getName(),
                            Type.getDescriptor(fieldObject.getType()),
                            visitor.toString(),
                            null
                    );
                } else {
                    // local
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
            }
            break;
            case CONSTINT: {
                ConstInt i = (ConstInt) instruction;
                String value = i.getValue();
                int val = Integer.parseInt(value);
                if (Byte.MIN_VALUE < val && val <= Byte.MAX_VALUE) {
                    methodVisitor.visitIntInsn(Opcodes.BIPUSH, val);
                } else if (Short.MIN_VALUE < val && val <= Byte.MAX_VALUE) {
                    methodVisitor.visitIntInsn(Opcodes.SIPUSH, val);
                } else {
                    methodVisitor.visitLdcInsn(val);
                }
            }
            break;
            case CONSTSTRING: {
                ConstString s = (ConstString) instruction;
                methodVisitor.visitLdcInsn(s.getValue());
            }
            break;
            case ENDCLASS: {
                classWriter.visitEnd();
                byte[] bytes = classWriter.toByteArray();
                context.addBytecode(new File(currentClassName + ".class").toPath(), bytes);
            }
            break;
            case ENDMETHOD:
            case ENDCONSTRUCTOR: {
                localVarNames.pop();
                methodVisitor.visitMaxs(0, 0);
                methodVisitor.visitEnd();
            }
            break;
            case CAST: {
                Cast cast = (Cast) instruction;
                ClassAttribute classAttribute = cast.getAttribute(ClassAttribute.class).orElseThrow(IllegalStateException::new);
                methodVisitor.visitTypeInsn(Opcodes.CHECKCAST, Type.getInternalName(classAttribute.getClassObject()));
            }
            break;
            case JUMPTARGET: {
                JumpTarget jumpTarget = (JumpTarget) instruction;
                Label l = new Label();
                labelMap.put(jumpTarget.getTargetName(), l);
                methodVisitor.visitLabel(l);
            }
            break;
            case JUMP: {
                Jump jump = (Jump) instruction;
                Label l = labelMap.get(jump.getLabel());
                methodVisitor.visitJumpInsn(Opcodes.GOTO, l);
            }
            break;
            case STORE: {
                Store store = (Store) instruction;
                localVarNames.peek().add(store.getStoreName().getSimpleName());
                methodVisitor.visitVarInsn(Opcodes.ASTORE, localVarNames.size() - 1);
            }
            break;
            case IFJUMPFALSE: {
                IfJumpFalse ifJumpFalse = (IfJumpFalse) instruction;
                Label label = labelMap.get(ifJumpFalse.getJumpTarget());
                methodVisitor.visitJumpInsn(Opcodes.IFEQ, label);
            }
            break;
            case IFJUMPTRUE: {
                IfJumpFalse ifJumpFalse = (IfJumpFalse) instruction;
                Label label = labelMap.get(ifJumpFalse.getJumpTarget());
                methodVisitor.visitJumpInsn(Opcodes.IFNE, label);
            }
            break;
            case BEGINBLOCK: {
                BeginBlock beginBlock = (BeginBlock) instruction;
                localVarNames.push(new ArrayList<>());
                blockNames.add(beginBlock.getLabel().getName());
            }
            break;
            case ENDBLOCK: {
                EndBlock endBlock = (EndBlock) instruction;
                localVarNames.pop();
                if (!blockNames.contains(endBlock.getLabel().getName())) {

                }
            }
            break;
            default:
                LOGGER.debug("Ignored instruction : {}", instruction);
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
