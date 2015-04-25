/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree.classdecls;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import karaffe.compiler.tree.ASMConvertible;
import karaffe.compiler.tree.AST;
import karaffe.compiler.tree.AbstractNode;
import karaffe.compiler.visitor.Visitor;

public class AutoDeclList extends AbstractNode implements ASMConvertible<List<ASMConvertible<?>>> {

    private final Optional<AST> autoDecl;
    private final Optional<AST> autoDeclList;

    public AutoDeclList(Object d) {
        this(d, null);
    }

    public AutoDeclList(Object d, Object l) {
        this.autoDecl = Optional.ofNullable((AST) d);
        this.autoDeclList = Optional.ofNullable((AST) l);
        addChildren(autoDecl);
        addChildren(autoDeclList);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.autoDeclList(this);
    }

    @Override
    public String toString() {
        return "(AutoDeclList:" + String.join(",", autoDecl.toString(), autoDeclList.toString()) + ")";
    }

    @Override
    public List<ASMConvertible<?>> toNode() {
        List<ASMConvertible<?>> fieldNodes = new ArrayList<>();
        fieldNodes.add(AutoDecl.class.cast(autoDecl.get()));
        autoDeclList.ifPresent(l -> fieldNodes.addAll(AutoDeclList.class.cast(l).toNode()));
        return fieldNodes;
    }

}
