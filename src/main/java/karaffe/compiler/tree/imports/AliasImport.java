/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree.imports;

import karaffe.compiler.tree.AST;
import karaffe.compiler.tree.AbstractNode;
import karaffe.compiler.visitor.Visitor;

public class AliasImport extends AbstractNode {

    private final AST beforeAmbiguousName;
    private final AST identifier;

    public AliasImport(Object n, Object i) {
        this.beforeAmbiguousName = (AST) n;
        this.identifier = (AST) i;
        addChildren(beforeAmbiguousName);
        addChildren(identifier);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.aliasImport(this);
    }

    @Override
    public String toString() {
        return "(AliasImport:" + name + "," + id + ")";
    }

}
