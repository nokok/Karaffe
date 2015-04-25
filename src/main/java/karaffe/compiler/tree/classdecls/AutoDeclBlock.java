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

public class AutoDeclBlock extends AbstractNode implements ASMConvertible<List<ASMConvertible<?>>> {

    private final Optional<AST> autoDeclList;

    public AutoDeclBlock(Object l) {
        this.autoDeclList = Optional.ofNullable((AST) l);
        addChildren(autoDeclList);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.autoDeclBlock(this);
    }

    @Override
    public String toString() {
        return "(AutoDeclBlock:" + autoDeclList.toString() + ")";
    }

    @Override
    public List<ASMConvertible<?>> toNode() {
        return autoDeclList.map(l -> AutoDeclList.class.cast(l).toNode()).orElse(new ArrayList<>(0));
    }

}
