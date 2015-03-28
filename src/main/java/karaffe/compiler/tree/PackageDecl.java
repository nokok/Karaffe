/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree;

import karaffe.compiler.phase.parser.Lexer;
import karaffe.compiler.visitor.Visitor;

public class PackageDecl extends AbstractNode {

    private final AmbiguousName name;

    public PackageDecl(AmbiguousName name) {
        super(null);
        this.name = name;
    }

    public PackageDecl(Lexer.SymInfo symInfo, AmbiguousName name) {
        super(symInfo);
        this.name = name;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.packageDecl(this);
    }

    public AmbiguousName name() {
        return name;
    }

    @Override
    public String toString() {
        return "PackageDecl:" + name.toString();
    }

}
