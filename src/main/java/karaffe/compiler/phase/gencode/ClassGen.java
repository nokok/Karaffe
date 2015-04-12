/**
 * Karaffe Programming Language
 */
package karaffe.compiler.phase.gencode;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import karaffe.compiler.tree.AST;
import karaffe.compiler.tree.classdecls.SimpleClassDecl;
import karaffe.compiler.tree.compileunits.FileNode;
import karaffe.compiler.visitor.Visitor;
import karaffe.compiler.visitor.VisitorAdaptor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

public class ClassGen implements Function<AST, List<ByteCode>> {

    private final List<ByteCode> bytecodes = new ArrayList<>();

    private final Visitor visitor = new VisitorAdaptor() {
        private final ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);

        @Override
        public void fileNode(FileNode aThis) {
            classWriter.visitSource(aThis.fileName(), null);
        }

        @Override
        public void simpleClassDecl(SimpleClassDecl clazz) {
            classWriter.visit(Opcodes.V1_8, clazz.access(), clazz.name(), clazz.signature(), clazz.superName(), clazz.interfaces());
            bytecodes.add(new ByteCode(classWriter.toByteArray(), clazz.name() + ".class"));
        }

    };

    public List<ByteCode> getBytecodes() {
        return bytecodes;
    }

    @Override
    public List<ByteCode> apply(AST ast) {
        ast.accept(visitor);
        return bytecodes;
    }

}
