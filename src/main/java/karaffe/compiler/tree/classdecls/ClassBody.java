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

public class ClassBody extends AbstractNode implements ASMConvertible<List<ASMConvertible<?>>> {

    private final AST body;
    private final Optional<AST> classBody;

    public ClassBody(Object f, Object b) {
        this.body = (AST) f;
        this.classBody = Optional.ofNullable((AST) b);
        addChildren(body);
        addChildren(classBody);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.classBody(this);
    }

    @Override
    public String toString() {
        return "(ClassBody:" + body.toString() + ")";
    }

    @Override
    public List<ASMConvertible<?>> toNode() {
        List<ASMConvertible<?>> list = new ArrayList<>();
        list.add(ASMConvertible.class.cast(body));
        list.addAll(ClassBody.class.cast(classBody).toNode());
        return list;
    }

}
