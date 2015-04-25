/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree.compileunits;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import karaffe.compiler.phase.gencode.ByteCode;
import karaffe.compiler.tree.ASMConvertible;
import karaffe.compiler.tree.AST;
import karaffe.compiler.tree.AbstractNode;
import karaffe.compiler.tree.classdecls.ClassDeclList;
import karaffe.compiler.visitor.Visitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

public class CompileUnit extends AbstractNode implements ASMConvertible<List<ByteCode>> {

    private final AST file;
    private final Optional<AST> packageDecl;
    private final Optional<AST> importDecl;
    private final Optional<AST> classDeclList;

    public CompileUnit(File f, Object p, Object i, Object c) {
        this.file = new FileNode(f);
        this.packageDecl = Optional.ofNullable((AST) p);
        this.importDecl = Optional.ofNullable((AST) i);
        this.classDeclList = Optional.ofNullable((AST) c);
        addChildren(file);
        addChildren(packageDecl);
        addChildren(importDecl);
        addChildren(classDeclList);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.compileUnit(this);
    }

    @Override
    public String toString() {
        return "(CompileUnit:" + String.join(",", file.toString(), packageDecl.toString(), importDecl.toString(), classDeclList.toString()) + ")";
    }

    @Override
    public List<ByteCode> toNode() {
        List<ClassNode> classNodes = new ArrayList<>();
        classDeclList.ifPresent(l -> classNodes.addAll(ClassDeclList.class.cast(l).toNode()));
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        List<ByteCode> byteCodes = new ArrayList<>();
        classNodes.forEach(n -> {
            n.accept(classWriter);
            byteCodes.add(new ByteCode(classWriter.toByteArray(), n.name + ".class", ""));
        });
        return byteCodes;
    }

    public boolean hasPackageDecl() {
        return packageDecl.isPresent();
    }

    public boolean hasImportDecl() {
        return importDecl.isPresent();
    }

    public boolean hasClassDecl() {
        return classDeclList.isPresent();
    }

}
