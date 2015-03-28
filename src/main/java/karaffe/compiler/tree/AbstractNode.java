/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree;

import java.util.ArrayList;
import java.util.List;
import karaffe.compiler.phase.parser.Lexer.SymInfo;

public abstract class AbstractNode implements AST {

    protected final List<AST> children = new ArrayList<>();
    protected final SymInfo symbol;

    public AbstractNode() {
        this(null);
    }

    public AbstractNode(SymInfo symInfo) {
        this.symbol = symInfo;
    }

    @Override
    public List<AST> children() {
        return children;
    }

    public SymInfo symbol() {
        return symbol;
    }

}
