/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree.classdecls;

import java.util.Optional;
import karaffe.compiler.tree.AST;
import karaffe.compiler.tree.AbstractNode;
import karaffe.compiler.visitor.Visitor;

public class AutoDeclList extends AbstractNode {

    private final AST autoDecl;
    private final Optional<AST> autoDeclList;

    public AutoDeclList(Object d) {
        this(d, null);
    }

    public AutoDeclList(Object d, Object l) {
        this.autoDecl = (AST) d;
        this.autoDeclList = Optional.ofNullable((AST) l);
        children.add(autoDecl);
        autoDeclList.ifPresent(children::add);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.autoDeclList(this);
    }

    @Override
    public String toString() {
        return "(AutoDeclList:" + String.join(",", autoDecl.toString(), autoDeclList.toString()) + ")";
    }

}
