/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree.classdecls;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import karaffe.compiler.tree.AST;
import karaffe.compiler.tree.AbstractNode;
import karaffe.compiler.visitor.Visitor;
import org.objectweb.asm.tree.FieldNode;

public class ClassBody extends AbstractNode implements Supplier<List<? extends FieldNode>> {

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
    public List<FieldNode> get() {
        List<FieldNode> list = new ArrayList<>();
        FieldDecl decl = (FieldDecl) body;
        list.add(decl.get());
        classBody.ifPresent(c -> list.addAll(ClassBody.class.cast(c).get()));
        return list;
    }

}
