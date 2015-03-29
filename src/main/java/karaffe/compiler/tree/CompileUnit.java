/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree;

import java.io.File;
import java.util.Optional;
import karaffe.compiler.visitor.Visitor;

public class CompileUnit extends AbstractNode {

    private final File file;
    private final Optional<AST> packageDecl;
    private final Optional<AST> importDecl;

    public CompileUnit(File file, Object p, Object i) {
        this.file = file;
        this.packageDecl = Optional.ofNullable((AST) p);
        this.importDecl = Optional.ofNullable((AST) i);
        packageDecl.ifPresent(children::add);
        importDecl.ifPresent(children::add);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.compileUnit(this);
    }

    @Override
    public String toString() {
        return "(CompileUnit:" + String.join(",", "(File:" + file.toString() + ")", packageDecl.toString(), importDecl.toString()) + ")";
    }

}
