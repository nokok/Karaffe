/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree.type;

import java.util.Optional;
import karaffe.compiler.tree.AST;
import karaffe.compiler.tree.AbstractNode;
import karaffe.compiler.visitor.Visitor;

public class SimpleTypeList extends AbstractNode {

    private final AST type;
    private final Optional<AST> typeList;

    public SimpleTypeList(Object t, Object l) {
        this.type = (AST) t;
        this.typeList = Optional.ofNullable((AST) l);
        addChildren(type);
        addChildren(typeList);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.simpleTypeList(this);
    }

}
