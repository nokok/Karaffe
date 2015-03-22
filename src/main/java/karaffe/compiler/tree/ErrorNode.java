/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree;

import java.util.Arrays;
import java.util.List;
import karaffe.compiler.visitor.Visitor;

public class ErrorNode extends AbstractNode {

    private final List<Object> errors;
    private final String errorId;

    public ErrorNode(String errorId, Object... errors) {
        this.errorId = errorId;
        this.errors = Arrays.asList(errors);
    }

    @Override
    public void accept(Visitor visitor) {
    }

}
