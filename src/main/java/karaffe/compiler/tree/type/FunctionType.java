/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree.type;

import java.util.Optional;
import karaffe.compiler.tree.AST;
import karaffe.compiler.tree.AbstractNode;
import karaffe.compiler.visitor.Visitor;

public class FunctionType extends AbstractNode {

    private final AST type;
    private final Optional<AST> functionType;

    public FunctionType(Object t, Object f) {
        this.type = (AST) t;
        this.functionType = Optional.ofNullable((AST) f);
        children.add(type);
        functionType.ifPresent(children::add);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.functionType(this);
    }

}
