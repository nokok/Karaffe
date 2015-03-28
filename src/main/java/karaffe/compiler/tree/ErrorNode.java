/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree;

import java.util.Arrays;
import java.util.List;
import karaffe.compiler.phase.parser.Lexer;
import karaffe.compiler.visitor.Visitor;

public class ErrorNode extends AbstractNode {

    private final List<Object> errors;
    private final String errorId;

    public ErrorNode(String errorId, Object... errors) {
        this(null, errorId, errors);
    }

    public ErrorNode(Lexer.SymInfo symInfo, String errorId, Object... errors) {
        super(symInfo);
        this.errorId = errorId;
        this.errors = Arrays.asList(errors);
    }

    @Override
    public void accept(Visitor visitor) {
    }
}
