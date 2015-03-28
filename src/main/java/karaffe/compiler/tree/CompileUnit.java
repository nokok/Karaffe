/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree;

import karaffe.compiler.phase.parser.Lexer;
import karaffe.compiler.visitor.Visitor;

public class CompileUnit extends AbstractNode {

    private final AST packageDecl;

    public CompileUnit(AST node) {
        super(null);
        this.packageDecl = node;
    }

    public CompileUnit(Lexer.SymInfo symInfo, PackageDecl packageDecl) {
        super(symInfo);
        this.packageDecl = packageDecl;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.compileUnit(this);
    }

    @Override
    public String toString() {
        return "CompileUnit:" + packageDecl.toString();
    }

}
