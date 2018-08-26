package org.karaffe.compiler.backend.jvm.tasks;

import net.nokok.azm.ClassWriter;
import net.nokok.azm.Opcodes;
import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.ir.IR;
import org.karaffe.compiler.base.mir.instructions.DeprecatedInstruction;
import org.karaffe.compiler.base.mir.instructions.attr.JavaModifier;
import org.karaffe.compiler.base.task.AbstractTask;
import org.karaffe.compiler.base.task.MIRTask;
import org.karaffe.compiler.base.task.TaskResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class GenJavaByteCodeTask extends AbstractTask implements MIRTask {

    private ClassWriter classWriter;
    private static final Logger LOGGER = LoggerFactory.getLogger(GenJavaByteCodeTask.class);

//    @Override
//    public TaskResult run(DeprecatedInstructions instructions, CompilerContext context) {
//        List<String> classNames = new ArrayList<>();
//        List<String> methodNames = new ArrayList<>();
//        List<String> blockNames = new ArrayList<>();
//        Map<org.karaffe.compiler.base.mir.instructions.util.Label, Label> labelMap = new HashMap<>();
//        Stack<List<String>> localVarNames = new Stack<>();
//        localVarNames.push(new ArrayList<>());
//        int currentJavaAPI = Opcodes.V1_8;
//
//        String currentClassName = "";
//        ClassVisitor classVisitor = null;
//        MethodVisitor methodVisitor = null;
//        FieldVisitor fieldVisitor = null;
//        for (DeprecatedInstruction instruction : instructions) {
//            LOGGER.debug("Instruction : {}", instruction);
//            switch (instruction.getInstType()) {
//            case BEGINCLASS: {
//                BeginClass b = (BeginClass) instruction;
//                if (!b.hasAttribute(NewClassAttribute.class)) {
//                    context.addReport(Errors.internalError(b.getPosition(), "No NewClassAttribute found."));
//                    return TaskResult.FAILED;
//                }
//                NewClassAttribute newClassAttribute = b.getAttribute(NewClassAttribute.class).orElseThrow(IllegalStateException::new);
//                classNames.add(currentClassName);
//                classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
//                classWriter.visit(
//                        currentJavaAPI,
//                        newClassAttribute.getModifiers(),
//                        b.getClassName(),
//                        null,
//                        Type.getInternalName(newClassAttribute.getSuperClass()),
//                        newClassAttribute.getInterfaces().stream().map(Type::getInternalName).collect(Collectors.toList()).toArray(new String[]{})
//                );
//            }
//            break;
//            case BEGINMETHOD: {
//                localVarNames.push(new ArrayList<>());
//                BeginMethod beginMethod = (BeginMethod) instruction;
//                if (!beginMethod.hasAttribute(NewMethodAttribute.class)) {
//                    context.addReport(Errors.internalError(beginMethod.getPosition(), "No NewMethodAttribute found."));
//                    return TaskResult.FAILED;
//                }
//                NewMethodAttribute methodClassAttribute = beginMethod.getAttribute(NewMethodAttribute.class).orElseThrow(IllegalStateException::new);
//                methodNames.add(beginMethod.getMethodName());
//                methodVisitor = classWriter.visitMethod(
//                        methodClassAttribute.getModifiers(),
//                        methodClassAttribute.getName(),
//                        Type.getMethodDescriptor(Type.getType(methodClassAttribute.getReturnType()), methodClassAttribute.getParameters()),
//                        null,
//                        methodClassAttribute.getThrows().stream().map(Type::getInternalName).collect(Collectors.toList()).toArray(new String[]{})
//                );
//            }
//            break;
//            case BEGINCONSTRUCTOR: {
//                localVarNames.push(new ArrayList<>());
//                BeginConstructor beginConstructor = (BeginConstructor) instruction;
//                methodNames.add(beginConstructor.getLabel().getSimpleName());
//                if (!beginConstructor.hasAttribute(NewConstructorAttribute.class)) {
//                    context.addReport(Errors.internalError(beginConstructor.getPosition(), "No NewConstructorAttribute found."));
//                    return TaskResult.FAILED;
//                }
//                NewConstructorAttribute constructorAttribute = beginConstructor.getAttribute(NewConstructorAttribute.class).orElseThrow(IllegalStateException::new);
//                methodVisitor = classWriter.visitMethod(
//                        constructorAttribute.getModifiers(),
//                        "<init>",
//                        Type.getMethodDescriptor(Type.getType(constructorAttribute.getReturnType()), constructorAttribute.getParameters()),
//                        null,
//                        constructorAttribute.getThrows().stream().map(Type::getInternalName).collect(Collectors.toList()).toArray(new String[]{})
//                );
//            }
//            break;
//            case LOAD: {
//                Load load = (Load) instruction;
//                String simpleName = load.getLoadName().getSimpleName();
//                if (simpleName.equals("this")) {
//                    methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
//                } else if (simpleName.equals("super")) {
//                    methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
//                } else {
//                    int index = localVarNames.peek().indexOf(simpleName);
//                    if (index == -1) {
//                        LOGGER.error("Symbol not found on Load Instruction : " + load.getLoadName());
//                        context.addReport(Errors.symbolNotFound(load.getPosition(), simpleName));
//                        return TaskResult.FAILED;
//                    }
//                    methodVisitor.visitVarInsn(Opcodes.ALOAD, index);
//                }
//            }
//            break;
//            case INVOKE: {
//                Invoke invoke = (Invoke) instruction;
//                int opcode;
//                String owner;
//                String name;
//                String descriptor;
//                boolean isInterface;
//                if (invoke.hasAttribute(InvokeConstructorAttribute.class)) {
//                    InvokeConstructorAttribute invokeConstructorAttribute = invoke.getAttribute(InvokeConstructorAttribute.class).orElseThrow(IllegalStateException::new);
//                    opcode = Opcodes.INVOKESPECIAL;
//                    owner = invokeConstructorAttribute.getConstructorOwner();
//                    name = "<init>";
//                    descriptor = invokeConstructorAttribute.getDescriptor();
//                    isInterface = false;
//                } else if (invoke.hasAttribute(InvokeMethodAttribute.class)) {
//                    InvokeMethodAttribute invokeMethodAttribute = invoke.getAttribute(InvokeMethodAttribute.class).orElseThrow(IllegalStateException::new);
//                    if (invokeMethodAttribute.isPrivateMethod()) {
//                        //private method
//                        opcode = Opcodes.INVOKESPECIAL;
//                    } else {
//                        opcode = Opcodes.INVOKEVIRTUAL;
//                    }
//                    owner = invokeMethodAttribute.getMethodOwner();
//                    name = invokeMethodAttribute.getMethodName();
//                    descriptor = invokeMethodAttribute.getMethodDescriptor();
//                    isInterface = invokeMethodAttribute.isInterfaceMethod();
//                } else {
//                    context.addReport(Errors.internalError(invoke.getPosition(), "No attribute found at INVOKE"));
//                    return TaskResult.FAILED;
//                }
//                if (opcode == Opcodes.INVOKESPECIAL) {
//                    methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);// for invokespecial
//                }
//                methodVisitor.visitMethodInsn(
//                        opcode,
//                        owner,
//                        name,
//                        descriptor,
//                        isInterface
//                );
//            }
//            break;
//            case INVOKEMETHOD: {
//                InvokeMethod invokeMethod = (InvokeMethod) instruction;
//                int opcode;
//                String owner;
//                String name;
//                String descriptor;
//                boolean isInterface;
//                if (invokeMethod.hasAttribute(InvokeMethodAttribute.class)) {
//                    InvokeMethodAttribute invokeMethodAttribute = invokeMethod.getAttribute(InvokeMethodAttribute.class).orElseThrow(IllegalStateException::new);
//                    opcode = invokeMethodAttribute.isPrivateMethod() ? Opcodes.INVOKESPECIAL : Opcodes.INVOKEVIRTUAL;
//                    owner = invokeMethodAttribute.getMethodOwner();
//                    name = invokeMethodAttribute.getMethodName();
//                    descriptor = invokeMethodAttribute.getMethodDescriptor();
//                    isInterface = invokeMethodAttribute.isInterfaceMethod();
//                } else {
//                    throw new IllegalStateException("No attribute found at INVOKEMETHOD");
//                }
//
//                methodVisitor.visitMethodInsn(
//                        opcode,
//                        owner,
//                        name,
//                        descriptor,
//                        isInterface);
//            }
//            break;
//            case RETURN: {
//                Return returnInstruction = (Return) instruction;
//                if (!returnInstruction.hasAttribute(ReturnOpcodesAttribute.class)) {
//                    context.addReport(Errors.internalError(returnInstruction.getPosition(), "No ReturnOpcodesAttribute found."));
//                    return TaskResult.FAILED;
//                }
//                ReturnOpcodesAttribute returnOpcodesAttribute = returnInstruction.getAttribute(ReturnOpcodesAttribute.class).orElseThrow(IllegalStateException::new);
//                methodVisitor.visitInsn(returnOpcodesAttribute.getOpcodes());
//            }
//            break;
//            case VALDEF: {
//                ValDef valDef = (ValDef) instruction;
//                localVarNames.peek().add(valDef.getValName().getSimpleName());
//
//                if (valDef.hasAttribute(FieldClassAttribute.class)) {
//                    // field
//                    if (!valDef.hasAttribute(FieldClassAttribute.class)) {
//                        context.addReport(Errors.internalError(valDef.getPosition(), "No FieldClassAttribute found."));
//                        return TaskResult.FAILED;
//                    }
//                    FieldClassAttribute fieldClassAttribute = valDef.getAttribute(FieldClassAttribute.class).orElseThrow(IllegalStateException::new);
//                    Field fieldObject = fieldClassAttribute.getFieldObject();
//                    java.lang.reflect.Type genericType = fieldObject.getGenericType();
//
//                    SignatureReader reader = new SignatureReader(genericType.getTypeName());
//                    SignatureWriter visitor = new SignatureWriter();
//                    reader.accept(visitor);
//
//                    fieldVisitor = classVisitor.visitField(
//                            fieldObject.getModifiers(),
//                            fieldObject.getName(),
//                            Type.getDescriptor(fieldObject.getType()),
//                            visitor.toString(),
//                            null
//                    );
//                } else {
//                    // local
//                    if (!valDef.hasAttribute(TypedInstructionAttribute.class)) {
//                        context.addReport(Errors.internalError(valDef.getPosition(), "No TypedInstructionAttribute found."));
//                        return TaskResult.FAILED;
//                    }
//                    TypedInstructionAttribute typedInstructionAttribute = valDef.getAttribute(TypedInstructionAttribute.class).orElseThrow(IllegalStateException::new);
//                    if (!valDef.hasAttribute(JavaVMScopeAttribute.class)) {
//                        context.addReport(Errors.internalError(valDef.getPosition(), "No JavaVMScopeAttribute found."));
//                        return TaskResult.FAILED;
//                    }
//                    JavaVMScopeAttribute javaVMScopeAttribute = valDef.getAttribute(JavaVMScopeAttribute.class).orElseThrow(IllegalStateException::new);
//
//                    Class<?> valDefType = typedInstructionAttribute.getTypedInfo();
//
//                    if (valDef.hasAttribute(ParameterAttribute.class)) {
//                        methodVisitor.visitParameter(valDef.getValName().getSimpleName(), toJavaModifier(valDef));
//                    } else {
//                        methodVisitor.visitLocalVariable(
//                                valDef.getValName().getSimpleName(),
//                                Type.getInternalName(valDefType),
//                                null,
//                                javaVMScopeAttribute.getBegin(),
//                                javaVMScopeAttribute.getEnd(),
//                                localVarNames.peek().size() - 1);
//                    }
//                }
//            }
//            break;
//            case CONSTINT: {
//                ConstInt i = (ConstInt) instruction;
//                String value = i.getValue();
//                int val = Integer.parseInt(value);
//                if (Byte.MIN_VALUE < val && val <= Byte.MAX_VALUE) {
//                    methodVisitor.visitIntInsn(Opcodes.BIPUSH, val);
//                } else if (Short.MIN_VALUE < val && val <= Byte.MAX_VALUE) {
//                    methodVisitor.visitIntInsn(Opcodes.SIPUSH, val);
//                } else {
//                    methodVisitor.visitLdcInsn(val);
//                }
//            }
//            break;
//            case CONSTSTRING: {
//                ConstString s = (ConstString) instruction;
//                methodVisitor.visitLdcInsn(s.getValue());
//            }
//            break;
//            case ENDCLASS: {
//                classWriter.visitEnd();
//                byte[] bytes = classWriter.toByteArray();
//                context.addBytecode(new File(currentClassName + ".class").toPath(), bytes);
//            }
//            break;
//            case ENDMETHOD:
//            case ENDCONSTRUCTOR: {
//                localVarNames.pop();
//                methodVisitor.visitMaxs(0, 0);
//                methodVisitor.visitEnd();
//            }
//            break;
//            case CAST: {
//                Cast cast = (Cast) instruction;
//                ClassAttribute classAttribute = cast.getAttribute(ClassAttribute.class).orElseThrow(IllegalStateException::new);
//                methodVisitor.visitTypeInsn(Opcodes.CHECKCAST, Type.getInternalName(classAttribute.getClassObject()));
//            }
//            break;
//            case JUMPTARGET: {
//                JumpTarget jumpTarget = (JumpTarget) instruction;
//                Label l = new Label();
//                labelMap.put(jumpTarget.getTargetName(), l);
//                methodVisitor.visitLabel(l);
//            }
//            break;
//            case JUMP: {
//                Jump jump = (Jump) instruction;
//                Label l = labelMap.get(jump.getLabel());
//                methodVisitor.visitJumpInsn(Opcodes.GOTO, l);
//            }
//            break;
//            case STORE: {
//                Store store = (Store) instruction;
//                localVarNames.peek().add(store.getStoreName().getSimpleName());
//                methodVisitor.visitVarInsn(Opcodes.ASTORE, localVarNames.size() - 1);
//            }
//            break;
//            case IFJUMPFALSE: {
//                IfJumpFalse ifJumpFalse = (IfJumpFalse) instruction;
//                Label label = labelMap.get(ifJumpFalse.getJumpTarget());
//                methodVisitor.visitJumpInsn(Opcodes.IFEQ, label);
//            }
//            break;
//            case IFJUMPTRUE: {
//                IfJumpFalse ifJumpFalse = (IfJumpFalse) instruction;
//                Label label = labelMap.get(ifJumpFalse.getJumpTarget());
//                methodVisitor.visitJumpInsn(Opcodes.IFNE, label);
//            }
//            break;
//            case BEGINBLOCK: {
//                BeginBlock beginBlock = (BeginBlock) instruction;
//                localVarNames.push(new ArrayList<>());
//                blockNames.add(beginBlock.getLabel().getName());
//            }
//            break;
//            case ENDBLOCK: {
//                localVarNames.pop();
//            }
//            break;
//            default:
//                LOGGER.debug("Ignored instruction : {}", instruction);
//            }
//        }
//        return TaskResult.SUCCESSFUL;
//    }


    @Override
    public TaskResult run(IR ir, CompilerContext context) {
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

    private int toJavaModifier(DeprecatedInstruction instruction) {
        return toJavaModifier(instruction.getAttributes().stream().filter(i -> i instanceof JavaModifier).map(JavaModifier.class::cast).collect(Collectors.toList()));
    }

    private int toJavaModifier(List<JavaModifier> mods) {
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
