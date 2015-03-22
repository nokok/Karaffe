/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree;

import java.util.List;
import karaffe.compiler.visitor.Visitor;

public interface AST {

    public void accept(Visitor visitor);

    public List<AST> children();

}
