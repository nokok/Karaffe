/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree.imports;

import java.util.Optional;
import karaffe.compiler.tree.AST;
import karaffe.compiler.tree.AbstractNode;
import karaffe.compiler.visitor.Visitor;

public class ImportDeclList extends AbstractNode {

    private final AST import_;
    private final Optional<AST> importList;

    public ImportDeclList(Object import_, Object importList) {
        this.import_ = (AST) import_;
        this.importList = Optional.ofNullable((AST) importList);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.importDeclList(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(ImportDeclList:");
        if (importList == null) {
            sb.append(import_);
        } else {
            sb.append(import_).append(",").append(importList);
        }
        return sb.append(")").toString();

    }

}
