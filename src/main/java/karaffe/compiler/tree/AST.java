/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree;

import java.util.List;
import karaffe.compiler.visitor.Visitor;

public interface AST {

    public void accept(Visitor visitor);

    public default void childrenAccept(Visitor visitor) {
        children().forEach(c -> c.accept(visitor));
    }

    public List<AST> children();

}
