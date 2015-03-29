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
    private final int column;
    private final String errorId;
    private final int line;

    public ErrorNode(int line, int column, String errorId, Object... errors) {
        this.line = line;
        this.column = column;
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
        sb.append("(ErrorNode:").append("Id:").append(errorId).append(" at:line").append(line).append(", column:").append(column);
        errors.stream()
                .filter(Objects::nonNull)
                .map(err -> err.toString())
                .forEach(sb::append);
        return sb.append(")").toString();
    }

}
