package org.karaffe.compiler.backend.jvm.tasks.visitors;

import net.nokok.azm.ClassWriter;
import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.ir.IRVisitorAdaptor;
import org.karaffe.compiler.base.ir.Module;

public class ModuleVisitor extends IRVisitorAdaptor<byte[]> {

    public ModuleVisitor(CompilerContext context) {

    }

    @Override
    public byte[] visit(Module module) {
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        return writer.toByteArray();
    }
}
