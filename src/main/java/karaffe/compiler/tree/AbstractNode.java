/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractNode implements AST {

    protected final List<AST> children = new ArrayList<>();

    @Override
    public List<AST> children() {
        return children;
    }

}
