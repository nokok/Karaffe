/**
 * Karaffe Programming Language
 */
package karaffe.compiler.phase.gencode;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import karaffe.compiler.tree.AST;
import karaffe.compiler.tree.classdecls.FieldDecl;
import karaffe.compiler.tree.classdecls.SimpleClassDecl;
import karaffe.compiler.tree.compileunits.FileNode;
import karaffe.compiler.tree.compileunits.PackageDecl;
import karaffe.compiler.visitor.Visitor;
import karaffe.compiler.visitor.VisitorAdaptor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

public class ClassGen implements Function<AST, List<ByteCode>> {

    private final List<ByteCode> bytecodes = new ArrayList<>();

    private final Visitor classVisitor = new VisitorAdaptor() {
        private final ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);

        private Optional<String> packagePrefix = Optional.empty();

        @Override
        public void fileNode(FileNode aThis) {
            super.fileNode(aThis);
            classWriter.visitSource(aThis.fileName(), null);
        }

        @Override
        public void simpleClassDecl(SimpleClassDecl clazz) {
            super.simpleClassDecl(clazz);
            classWriter.visit(Opcodes.V1_8, clazz.access(), getClassNamePrefix() + clazz.name(), clazz.signature(), clazz.superName(), clazz.interfaces());
            bytecodes.add(new ByteCode(classWriter.toByteArray(), clazz.name() + ".class", packagePrefix.orElse("")));
        }

        @Override
        public void fieldDecl(FieldDecl aThis) {
            classWriter.visitField(aThis.access(), aThis.name(), aThis.desc(), aThis.signature(), aThis.value());
        }

        @Override
        public void packageDecl(PackageDecl aThis) {
            super.packageDecl(aThis);
            packagePrefix = Optional.of(aThis.toPath("/"));
        }

        private String getClassNamePrefix() {
            return packagePrefix.isPresent() ? packagePrefix.get() + "/" : "";
        }
    };

    public List<ByteCode> getBytecodes() {
        return bytecodes;
    }

    @Override
    public List<ByteCode> apply(AST ast) {
        ast.accept(classVisitor);
        return bytecodes;
    }

}
