/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree.type;

import karaffe.compiler.tree.ASMConvertible;
import karaffe.compiler.tree.AST;
import karaffe.compiler.tree.AbstractNode;
import karaffe.compiler.tree.name.AmbiguousName;
import karaffe.compiler.visitor.Visitor;

public class TypeName extends AbstractNode implements ASMConvertible<String> {

    private final AST name;

    public TypeName(Object n) {
        this.name = (AST) n;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.typeName(this);
    }

    @Override
    public String toString() {
        return "(TypeName:" + name + ")";
    }

    @Override
    public String toNode() {
        return AmbiguousName.class.cast(name).toPath("/");
    }
}
