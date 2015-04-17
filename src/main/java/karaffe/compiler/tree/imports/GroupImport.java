/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree.imports;

import karaffe.compiler.tree.AST;
import karaffe.compiler.tree.AbstractNode;
import karaffe.compiler.visitor.Visitor;

public class GroupImport extends AbstractNode {

    private final AST ambiguousName;
    private final AST otherImports;

    public GroupImport(Object name, Object idList) {
        this.ambiguousName = (AST) name;
        this.otherImports = (AST) idList;
        addChildren(ambiguousName);
        addChildren(otherImports);
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
