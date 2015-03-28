/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree;

import karaffe.compiler.phase.parser.Lexer;
import karaffe.compiler.visitor.Visitor;

public class Identifier extends AbstractNode {

    private final String id;

    public Identifier(Lexer.SymInfo symInfo, String id) {
        super(symInfo);
        this.id = id;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.identifier(this);
    }

    public String id() {
        return id;
    }

    @Override
    public String toString() {
        return "Identifier:" + id;
    }
}
