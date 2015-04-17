/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree.compileunits;

import java.io.File;
import java.util.Optional;
import karaffe.compiler.tree.AST;
import karaffe.compiler.tree.AbstractNode;
import karaffe.compiler.visitor.Visitor;

public class CompileUnit extends AbstractNode {

    private final AST file;
    private final Optional<AST> packageDecl;
    private final Optional<AST> importDecl;
    private final Optional<AST> classDecl;

    public CompileUnit(File f, Object p, Object i, Object c) {
        this.file = new FileNode(f);
        this.packageDecl = Optional.ofNullable((AST) p);
        this.importDecl = Optional.ofNullable((AST) i);
        this.classDecl = Optional.ofNullable((AST) c);
        addChildren(file);
        addChildren(packageDecl);
        addChildren(importDecl);
        addChildren(classDecl);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.compileUnit(this);
    }

    @Override
    public String toString() {
        return "(CompileUnit:" + String.join(",", file.toString(), packageDecl.toString(), importDecl.toString(), classDecl.toString()) + ")";
    }

}
