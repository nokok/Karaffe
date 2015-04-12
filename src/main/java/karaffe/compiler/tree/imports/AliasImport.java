/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree.imports;

import karaffe.compiler.tree.AST;
import karaffe.compiler.tree.AbstractNode;
import karaffe.compiler.visitor.Visitor;

public class AliasImport extends AbstractNode {

    private final AST name;
    private final AST id;

    public AliasImport(Object name, Object id) {
        this.name = (AST) name;
        this.id = (AST) id;
        children.add(this.name);
        children.add(this.id);
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
