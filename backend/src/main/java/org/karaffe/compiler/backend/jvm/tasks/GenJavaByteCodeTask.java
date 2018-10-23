package org.karaffe.compiler.backend.jvm.tasks;

import net.nokok.azm.ClassWriter;
import net.nokok.azm.Opcodes;
import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.ir.IR;
import org.karaffe.compiler.base.task.AbstractTask;
import org.karaffe.compiler.base.task.MIRTask;
import org.karaffe.compiler.base.task.TaskResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenJavaByteCodeTask extends AbstractTask implements MIRTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenJavaByteCodeTask.class);

    @Override
    public TaskResult run(IR ir, CompilerContext context) {
        final int bytecodeVersion = Opcodes.V1_8;
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
//        for (Module module : ir.getModules()) {
//            String moduleName = module.getModuleName();
//            int access = Opcodes.ACC_PUBLIC;
//            String name = moduleName;
//            String signature = "";
//            String superName = Type.getInternalName(Object.class);
//            String[] interfaces = new String[0];
//            classWriter.visit(bytecodeVersion, access, name, signature, superName, interfaces);
//            classWriter.visitEnd();
//            byte[] byteCode = classWriter.toByteArray();
//            context.addBytecode(Paths.get(moduleName + ".class"), byteCode);
//        }
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

}
