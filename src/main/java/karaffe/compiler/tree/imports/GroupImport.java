/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree.imports;

import karaffe.compiler.tree.AST;
import karaffe.compiler.tree.AbstractNode;
import karaffe.compiler.visitor.Visitor;

public class GroupImport extends AbstractNode {

    private final AST name;
    private final AST idList;

    public GroupImport(Object name, Object idList) {
        this.name = (AST) name;
        this.idList = (AST) idList;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.groupImport(this);
    }

    @Override
    public String toString() {
        return "(GroupImport:" + name + "," + idList + ")";
    }

}
