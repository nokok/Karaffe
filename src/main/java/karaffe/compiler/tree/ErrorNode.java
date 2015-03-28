/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import karaffe.compiler.visitor.Visitor;

public class ErrorNode extends AbstractNode {

    private final List<Object> errors;
    private final String errorId;

    public ErrorNode(int line, int column, String errorId, Object... errors) {
        this.errorId = errorId;
        this.errors = Arrays.asList(errors);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.errorNode(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(ErrorNode:").append(errorId);
        errors.stream()
                .filter(Objects::nonNull)
                .map(err -> err.toString())
                .forEach(sb::append);
        return sb.append(")").toString();
    }

}
