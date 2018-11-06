package org.karaffe.compiler.backend.jvm.tasks.visitors;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.attr.FilePathAttribute;
import org.karaffe.compiler.base.ir.IRVisitorAdaptor;
import org.karaffe.compiler.base.ir.Module;

import java.nio.file.Paths;
import java.util.Objects;

public class GenJavaByteCodeVisitor extends IRVisitorAdaptor<CompilerContext> {

    private final CompilerContext context;

    public GenJavaByteCodeVisitor(CompilerContext context) {
        this.context = Objects.requireNonNull(context);
    }

    @Override
    public CompilerContext visit(Module module) {
        FilePathAttribute filePathAttribute = module.getAttribute(FilePathAttribute.class).orElseThrow(IllegalStateException::new);
        byte[] byteCode = module.accept(new ModuleVisitor(context));
        context.addBytecode(filePathAttribute.getFilePath(), byteCode);
        return context;
    }
}
