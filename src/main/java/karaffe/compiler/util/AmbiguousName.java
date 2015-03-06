/**
 * Karaffe Programming Language
 */
package karaffe.compiler.util;

import java.util.ArrayList;
import java.util.List;
import karaffe.compiler.ParserException;
import karaffe.compiler.phase.parser.ASTIdentifier;
import karaffe.compiler.phase.parser.Node;
import karaffe.compiler.phase.parser.ParserDefaultVisitor;

public class AmbiguousName {

    private String pathCache;

    private final ParserDefaultVisitor visitor = new ParserDefaultVisitor() {

        @Override
        public void visit(ASTIdentifier node, Object data) throws ParserException {
            ids.add(node.jjtGetValue().toString());
        }
    };

    private final List<String> ids = new ArrayList<>();

    public AmbiguousName(Node node) {
        try {
            node.jjtAccept(visitor, this);
            if (ids.isEmpty()) {
                throw new IllegalStateException("Empty path");
            }
        } catch (ParserException ex) {
            throw new RuntimeException(ex);
        }
    }

    public String getPath() {
        if (pathCache != null) {
            return pathCache;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append(ids.get(0));
        ids.stream().skip(1).forEach(id -> {
            sb.append(".").append(id);
        });
        pathCache = sb.toString();
        return pathCache;
    }

    public String getLast() {
        return ids.get(ids.size() - 1);
    }

}
